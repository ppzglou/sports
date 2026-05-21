package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.remote.dto.EventDto
import gr.sppzglou.sports.data.remote.dto.SportDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DtoToDomainKtTest {

    @Test
    fun `SportDto maps correctly`() {
        val dto = SportDto(
            id = "FOOT",
            name = "Football",
            events = listOf(
                EventDto(
                    id = "1",
                    sportId = "FOOT",
                    competitor = "Barcelona-Real Madrid",
                    time = 1000L
                )
            )
        )

        val result = dto.toDomain()

        assertEquals("FOOT", result.id)
        assertEquals("Football", result.name)
        assertEquals(1, result.events.size)
    }

    @Test
    fun `SportDto with null name becomes empty string`() {
        val dto = SportDto(
            id = "FOOT",
            name = null,
            events = emptyList()
        )

        val result = dto.toDomain()

        assertEquals("", result.name)
    }

    @Test
    fun `SportDto with null events returns empty list`() {
        val dto = SportDto(
            id = "FOOT",
            name = "Football",
            events = null
        )

        val result = dto.toDomain()

        assertTrue(result.events.isEmpty())
    }

    @Test
    fun `EventDto maps correctly`() {
        val dto = EventDto(
            id = "1",
            sportId = "FOOT",
            competitor = "Barcelona-Real Madrid",
            time = 5000L
        )

        val result = dto.toDomain()

        assertEquals("1", result.id)
        assertEquals("FOOT", result.sportId)
        assertEquals("Barcelona", result.competitor1)
        assertEquals("Real Madrid", result.competitor2)
        assertEquals(5000L, result.time)
        assertFalse(result.isFav)
    }

    @Test
    fun `EventDto with null competitor becomes empty competitors`() {
        val result = EventDto(
            id = "1",
            sportId = "FOOT",
            competitor = null,
            time = 1000L
        ).toDomain()

        assertEquals("", result.competitor1)
        assertEquals("", result.competitor2)
    }

    @Test
    fun `EventDto with invalid competitor format keeps first competitor and empty second`() {
        val result = EventDto(
            id = "1",
            sportId = "FOOT",
            competitor = "OnlyOneTeam",
            time = 1000L
        ).toDomain()

        assertEquals("OnlyOneTeam", result.competitor1)
        assertEquals("", result.competitor2)
    }

    @Test
    fun `EventDto with null time becomes zero`() {
        val result = EventDto(
            id = "1",
            sportId = "FOOT",
            competitor = "A-B",
            time = null
        ).toDomain()

        assertEquals(0L, result.time)
    }

    @Test
    fun `EventDto is never favorite from remote mapping`() {
        val result = EventDto(
            id = "1",
            sportId = "FOOT",
            competitor = "A-B",
            time = 1000L
        ).toDomain()

        assertFalse(result.isFav)
    }
}