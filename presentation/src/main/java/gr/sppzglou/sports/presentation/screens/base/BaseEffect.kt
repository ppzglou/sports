package gr.sppzglou.sports.presentation.screens.base

interface BaseEffect

inline fun <reified T : BaseEffect> BaseEffect.on(
    crossinline block: (T) -> Unit
) {
    if (this is T) block(this)
}
