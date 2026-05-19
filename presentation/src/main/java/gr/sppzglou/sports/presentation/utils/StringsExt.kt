package gr.sppzglou.sports.presentation.utils

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource

enum class LettersFormat {
    CapitalizeFirst,
    CapitalizeWords,
    Uppercase,
    Lowercase,
    Unchanged,
}

@Composable
fun str(
    @StringRes id: Int,
    format: LettersFormat = LettersFormat.Unchanged,
): String {
    val txt = stringResource(id)

    return remember(txt, format) {
        textFormat(txt, format)
    }
}

fun textFormat(
    txt: String,
    format: LettersFormat,
): String =
    when (format) {
        LettersFormat.CapitalizeFirst ->
            txt.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }

        LettersFormat.CapitalizeWords ->
            txt.split(" ").joinToString(" ") {
                it.replaceFirstChar { c ->
                    if (c.isLowerCase()) c.titlecase() else c.toString()
                }
            }

        LettersFormat.Uppercase -> txt.uppercase()
        LettersFormat.Lowercase -> txt.lowercase()
        LettersFormat.Unchanged -> txt
    }