package gr.sppzglou.sports.presentation.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class CountdownFormatTest {

    @Test
    fun `zero millis returns zero seconds`() {
        assertEquals("0s", 0L.toCountdownText())
    }

    @Test
    fun `seconds only`() {
        assertEquals("45s", 45_000L.toCountdownText())
    }

    @Test
    fun `minutes and seconds`() {
        assertEquals("2m 5s", 125_000L.toCountdownText())
    }

    @Test
    fun `hours minutes seconds`() {
        assertEquals("1h 2m 3s", 3_723_000L.toCountdownText())
    }

    @Test
    fun `days hours minutes seconds`() {
        assertEquals("1d 1h 1m 1s", 90_061_000L.toCountdownText())
    }

    @Test
    fun `weeks days hours minutes seconds`() {
        val millis =
            (((1L * 7 + 2) * 24 * 60 * 60) + (3 * 60 * 60) + (4 * 60) + 5) * 1000

        assertEquals("1w 2d 3h 4m 5s", millis.toCountdownText())
    }

    @Test
    fun `months weeks days hours minutes seconds`() {
        val millis =
            (((((1L * 30 + 2 * 7 + 3) * 24 + 4) * 60 + 5) * 60) + 6) * 1000

        assertEquals("1mo 2w 3d 4h 5m 6s", millis.toCountdownText())
    }
}