package gr.sppzglou.sports.presentation.screens.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import gr.sppzglou.sports.domain.FailureWrapper
import gr.sppzglou.sports.domain.ResultWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface VmExt<T : BaseUiData> {
    fun updateData(transform: T.() -> T)
    fun setError(error: FailureWrapper)
    fun switchLoading(bool: Boolean)
}

abstract class BaseVM<
        S : BaseUiState<D>,
        D : BaseUiData,
        E : BaseEffect,
        >(
    initialState: S
) : ViewModel(), VmExt<D> {

    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState

    protected val _effects = MutableSharedFlow<E>()
    val effects = _effects.asSharedFlow()

    protected fun setState(reducer: (S) -> S) {
        _uiState.update(reducer)
    }

    protected fun updateData(
        transform: D.() -> D,
        stateMapper: (S, D) -> S
    ) {
        setState { state ->
            val currentData = state.data ?: return@setState state
            val newData = currentData.transform()
            stateMapper(state, newData)
        }
    }

    fun setError(message: String) = setError(FailureWrapper.Message(message))

    fun getDataOrNull(): D? =
        _uiState.value.data

    protected suspend fun emitEffect(effect: E) {
        _effects.emit(effect)
    }

    fun launch(
        onLoading: (isLoading: Boolean) -> Unit = {},
        onError: (errorMessage: String) -> Unit = {},
        block: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            onLoading(true)
            try {
                block()
            } catch (e: Exception) {
                onError(e.message ?: e.toString())
            } finally {
                onLoading(false)
            }
        }
    }

    fun <T> remoteCall(
        onLoading: (isLoading: Boolean) -> Unit = {},
        block: suspend () -> ResultWrapper<T>,
    ) {
        viewModelScope.launch {
            onLoading(true)
            try {
                block().also {
                    if (it.isFailure) {
                        setError(it.failureError)
                    }
                }
            } catch (e: Exception) {
                setError(e.message ?: e.toString())
            } finally {
                onLoading(false)
            }
        }
    }

    fun <T> remoteCallRes(
        call: suspend () -> ResultWrapper<T>,
        onLoading: (isLoading: Boolean) -> Unit = {},
        onError: (error: FailureWrapper) -> Unit = {},
        onSuccess: (T) -> Unit,
    ) {
        viewModelScope.launch {
            onLoading(true)
            try {
                call().also {
                    if (it.isFailure) {
                        setError(it.failureError)
                        onError(it.failureError)
                    }
                    if (it.isSuccess) {
                        onSuccess(it.successData)
                    }
                }
            } catch (e: Exception) {
                setError(e.message ?: e.toString())
            } finally {
                onLoading(false)
            }
        }
    }

    @Composable
    fun HandleEffect(
        lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
        onCollect: suspend (E) -> Unit
    ) {
        LaunchedEffect(this, lifecycleOwner) {
            lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                effects.collect { effect ->
                    onCollect(effect)
                }
            }
        }
    }
}
