package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DomainToEntityKtTest {

    @Test
    fun `SportDomain maps correctly to SportEntity`() {
        val domain = SportDomain(
            id = "FOOT",
            name = "Football",
            events = emptyList()
        )

        val (sportEntity, _) = domain.toEntity()

        assertEquals("FOOT", sportEntity.id)
        assertEquals("Football", sportEntity.name)
    }

    @Test
    fun `Events map correctly to EventEntities`() {
        val domain = SportDomain(
            id = "FOOT",
            name = "Football",
            events = listOf(
                EventDomain(
                    id = "1",
                    sportId = "FOOT",
                    competitor1 = "Barcelona",
                    competitor2 = "Real Madrid",
                    time = 1000L,
                    isFav = true
                )
            )
        )

        val (_, events) = domain.toEntity()

        assertEquals(1, events.size)

        val event = events.first()

        assertEquals("1", event.id)
        assertEquals("FOOT", event.sportId)
        assertEquals("Barcelona", event.competitor1)
        assertEquals("Real Madrid", event.competitor2)
        assertEquals(1000L, event.time)
        assertTrue(event.isFav)
    }

    @Test
    fun `Empty events list maps correctly`() {
        val domain = SportDomain(
            id = "FOOT",
            name = "Football",
            events = emptyList()
        )

        val (_, events) = domain.toEntity()

        assertTrue(events.isEmpty())
    }

    @Test
    fun `Multiple events map correctly`() {
        val domain = SportDomain(
            id = "FOOT",
            name = "Football",
            events = listOf(
                EventDomain(
                    id = "1",
                    sportId = "FOOT",
                    competitor1 = "A",
                    competitor2 = "B",
                    time = 1000L,
                    isFav = false
                ),
                EventDomain(
                    id = "2",
                    sportId = "FOOT",
                    competitor1 = "C",
                    competitor2 = "D",
                    time = 2000L,
                    isFav = true
                )
            )
        )

        val (_, events) = domain.toEntity()

        assertEquals(2, events.size)

        assertEquals("1", events[0].id)
        assertFalse(events[0].isFav)

        assertEquals("2", events[1].id)
        assertTrue(events[1].isFav)
    }

    @Test
    fun `SportEntity does not depend on events`() {
        val domain = SportDomain(
            id = "BASKET",
            name = "Basketball",
            events = listOf(
                EventDomain(
                    id = "1",
                    sportId = "BASKET",
                    competitor1 = "A",
                    competitor2 = "B",
                    time = 1000L,
                    isFav = false
                )
            )
        )

        val (sportEntity, _) = domain.toEntity()

        assertEquals("BASKET", sportEntity.id)
        assertEquals("Basketball", sportEntity.name)
    }

    @Test
    fun `Event favorite flag maps correctly`() {
        val favoriteEvent = EventDomain(
            id = "1",
            sportId = "FOOT",
            competitor1 = "A",
            competitor2 = "B",
            time = 1000L,
            isFav = true
        )

        val nonFavoriteEvent = EventDomain(
            id = "2",
            sportId = "FOOT",
            competitor1 = "C",
            competitor2 = "D",
            time = 2000L,
            isFav = false
        )

        val domain = SportDomain(
            id = "FOOT",
            name = "Football",
            events = listOf(favoriteEvent, nonFavoriteEvent)
        )

        val (_, events) = domain.toEntity()

        assertTrue(events[0].isFav)
        assertFalse(events[1].isFav)
    }
}