package gr.sppzglou.sports.presentation.screens.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gr.sppzglou.sports.domain.ResultWrapper


@Composable
fun <S : BaseUiState<D>, D : BaseUiData, E : BaseEffect> BaseRoute(
    vm: BaseVM<S, D, E>,
    onEffect: (E) -> Unit = {},
    screen: @Composable (data: ResultWrapper<D>) -> Unit,
) {
    val state by vm.uiState.collectAsStateWithLifecycle()

    vm.HandleEffect {
        onEffect(it)
    }

    screen(state.result)
}