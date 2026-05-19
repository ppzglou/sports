package gr.sppzglou.sports.presentation.screens.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun <S : BaseUiState<D>, D : BaseUiData, E : BaseEffect> BaseRoute(
    vm: BaseVM<S, D, E>,
    onEffect: (E) -> Unit = {},
    loadingView: @Composable () -> Unit = {},
    screen: @Composable (data: D) -> Unit,
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    vm.HandleEffect {
        onEffect(it)
    }

    state.data?.let { screen(it) } ?: loadingView()
}