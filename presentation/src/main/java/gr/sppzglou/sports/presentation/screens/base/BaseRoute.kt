package gr.sppzglou.sports.presentation.screens.base

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import gr.sppzglou.sports.domain.ResultWrapper


@Composable
fun <S : BaseUiState<D>, D : BaseUiData, E : BaseEffect> BaseRoute(
    vm: BaseVM<S, D, E>,
    onEffect: (Context, E) -> Unit = { _, _ -> },
    screen: @Composable (data: ResultWrapper<D>) -> Unit,
) {
    val context = LocalContext.current
    val state by vm.uiState.collectAsStateWithLifecycle()

    vm.HandleEffect {
        onEffect(context, it)
    }

    screen(state.result)
}