package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.local.entities.SportEventRow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class EntityToDomainKtTest {

    private fun row(
        sportId: String = "FOOT",
        sportName: String = "SOCCER",
        eventId: String? = "1",
        eventSportId: String? = sportId,
        competitor1: String? = "Team A",
        competitor2: String? = "Team B",
        time: Long? = 1779382322L,
        isFav: Boolean? = false,
    ) = SportEventRow(
        sportId = sportId,
        sportName = sportName,
        eventId = eventId,
        eventSportId = eventSportId,
        competitor1 = competitor1,
        competitor2 = competitor2,
        time = time,
        isFav = isFav
    )

    @Test
    fun `Empty input list mapping`() {
        val result = emptyList<SportEventRow>().toDomain()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `Single sport with single event mapping`() {
        val result = listOf(row()).toDomain()

        assertEquals(1, result.size)

        val sport = result.first()
        assertEquals("FOOT", sport.id)
        assertEquals("SOCCER", sport.name)
        assertEquals(1, sport.events.size)

        val event = sport.events.first()
        assertEquals("1", event.id)
        assertEquals("FOOT", event.sportId)
        assertEquals("Team A", event.competitor1)
        assertEquals("Team B", event.competitor2)
        assertEquals(1779382322L, event.time)
        assertEquals(false, event.isFav)
    }

    @Test
    fun `Multiple events grouping by sportId`() {
        val result = listOf(
            row(eventId = "1"),
            row(eventId = "2", competitor1 = "Team C", competitor2 = "Team D")
        ).toDomain()

        assertEquals(1, result.size)
        assertEquals("FOOT", result.first().id)
        assertEquals(2, result.first().events.size)
        assertEquals(listOf("1", "2"), result.first().events.map { it.id })
    }

    @Test
    fun `Multiple sports separation`() {
        val result = listOf(
            row(sportId = "FOOT", sportName = "SOCCER", eventId = "1"),
            row(sportId = "BASK", sportName = "BASKETBALL", eventId = "2")
        ).toDomain()

        assertEquals(2, result.size)
        assertEquals(listOf("FOOT", "BASK"), result.map { it.id })
    }

    @Test
    fun `Null eventId filtering`() {
        val result = listOf(
            row(eventId = null),
            row(eventId = "1")
        ).toDomain()

        assertEquals(1, result.size)
        assertEquals(1, result.first().events.size)
        assertEquals("1", result.first().events.first().id)
    }

    @Test
    fun `Null event fields exception handling`() {
        assertThrows(NullPointerException::class.java) {
            listOf(
                row(
                    eventId = "1",
                    competitor1 = null
                )
            ).toDomain()
        }
    }

    @Test
    fun `Sport metadata consistency`() {
        val result = listOf(
            row(sportId = "TENN", sportName = "TENNIS", eventId = "1"),
            row(sportId = "TENN", sportName = "TENNIS", eventId = "2")
        ).toDomain()

        assertEquals(1, result.size)
        assertEquals("TENN", result.first().id)
        assertEquals("TENNIS", result.first().name)
    }

    @Test
    fun `Event field mapping accuracy`() {
        val result = listOf(
            row(
                sportId = "BASK",
                sportName = "BASKETBALL",
                eventId = "55",
                eventSportId = "BASK",
                competitor1 = "Iraq",
                competitor2 = "Palestine",
                time = 1779843437L,
                isFav = true
            )
        ).toDomain()

        val event = result.first().events.first()

        assertEquals("55", event.id)
        assertEquals("BASK", event.sportId)
        assertEquals("Iraq", event.competitor1)
        assertEquals("Palestine", event.competitor2)
        assertEquals(1779843437L, event.time)
        assertEquals(true, event.isFav)
    }

    @Test
    fun `Sport without any events mapping`() {
        val result = listOf(
            row(
                sportId = "SNOO",
                sportName = "SNOOKER",
                eventId = null
            )
        ).toDomain()

        assertEquals(1, result.size)
        assertEquals("SNOO", result.first().id)
        assertEquals("SNOOKER", result.first().name)
        assertTrue(result.first().events.isEmpty())
    }

    @Test
    fun `Duplicate event entries`() {
        val result = listOf(
            row(eventId = "1"),
            row(eventId = "1")
        ).toDomain()

        assertEquals(1, result.size)
        assertEquals(2, result.first().events.size)
    }
}