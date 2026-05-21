package gr.sppzglou.sports.presentation.screens.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import gr.sppzglou.sports.domain.ResultWrapper
import gr.sppzglou.sports.domain.ResultWrapper.Companion.dataConverter
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseVM<
        S : BaseUiState<T>,
        T : BaseUiData,
        E : BaseEffect
        >(
    initialState: S
) : ViewModel() {

    protected val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<S> = _uiState

    protected val _effects = MutableSharedFlow<E>()
    val effects = _effects.asSharedFlow()

    protected fun setState(reducer: (S) -> S) {
        _uiState.update(reducer)
    }

    protected fun updateData(
        transform: T.() -> T,
        stateMapper: (S, ResultWrapper<T>) -> S
    ) {
        setState { state ->
            stateMapper(
                state,
                state.result.dataConverter { it.transform() }
            )
        }
    }

    protected fun getDataOrNull(): T? =
        _uiState.value.getDataOrNull()

    protected suspend fun emitEffect(effect: E) {
        _effects.emit(effect)
    }

    fun launch(
        loader: Boolean = false,
        block: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            block()
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
