package gr.sppzglou.sports.presentation.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class TextFormatTest {

    @Test
    fun `Unchanged returns same text`() {
        val result = textFormat(
            txt = "Hello World",
            format = LettersFormat.Unchanged
        )

        assertEquals("Hello World", result)
    }

    @Test
    fun `Uppercase converts text to uppercase`() {
        val result = textFormat(
            txt = "Hello World",
            format = LettersFormat.Uppercase
        )

        assertEquals("HELLO WORLD", result)
    }

    @Test
    fun `Lowercase converts text to lowercase`() {
        val result = textFormat(
            txt = "Hello World",
            format = LettersFormat.Lowercase
        )

        assertEquals("hello world", result)
    }

    @Test
    fun `CapitalizeFirst capitalizes only first character`() {
        val result = textFormat(
            txt = "hello world",
            format = LettersFormat.CapitalizeFirst
        )

        assertEquals("Hello world", result)
    }

    @Test
    fun `CapitalizeWords capitalizes every word`() {
        val result = textFormat(
            txt = "hello world test",
            format = LettersFormat.CapitalizeWords
        )

        assertEquals("Hello World Test", result)
    }

    @Test
    fun `Empty text returns empty text`() {
        val result = textFormat(
            txt = "",
            format = LettersFormat.CapitalizeFirst
        )

        assertEquals("", result)
    }
}