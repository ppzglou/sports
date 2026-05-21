package gr.sppzglou.sports.presentation.screens.dash.vm

import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class DashboardListMapperTest {

    @Test
    fun `empty sports returns empty list`() {
        val result = flattenSportsEventsList(emptyList())

        assertTrue(result.isEmpty())
    }

    @Test
    fun `single sport with no events returns only sport item`() {
        val result = flattenSportsEventsList(
            listOf(
                SportDomain(
                    id = "FOOT",
                    name = "Football",
                    events = emptyList()
                )
            )
        )

        assertEquals(1, result.size)
        assertTrue(result[0] is DashboardListItem.Sport)
    }

    @Test
    fun `sport with events returns sport and event items`() {
        val result = flattenSportsEventsList(
            listOf(sport())
        )

        assertEquals(3, result.size)

        assertTrue(result[0] is DashboardListItem.Sport)
        assertTrue(result[1] is DashboardListItem.Event)
        assertTrue(result[2] is DashboardListItem.Event)
    }

    @Test
    fun `multiple sports keep correct order`() {
        val result = flattenSportsEventsList(
            listOf(
                sport(id = "FOOT", name = "Football"),
                sport(id = "BASK", name = "Basketball")
            )
        )

        assertEquals(6, result.size)

        assertTrue(result[0] is DashboardListItem.Sport)
        assertTrue(result[1] is DashboardListItem.Event)
        assertTrue(result[2] is DashboardListItem.Event)

        assertTrue(result[3] is DashboardListItem.Sport)
        assertTrue(result[4] is DashboardListItem.Event)
        assertTrue(result[5] is DashboardListItem.Event)
    }

    @Test
    fun `event item contains correct sport name`() {
        val result = flattenSportsEventsList(
            listOf(sport(name = "Football"))
        )

        val eventItem = result[1] as DashboardListItem.Event

        assertEquals("Football", eventItem.sportName)
    }

    private fun sport(
        id: String = "FOOT",
        name: String = "Football"
    ) = SportDomain(
        id = id,
        name = name,
        events = listOf(
            EventDomain(
                id = "${id}_1",
                sportId = id,
                competitor1 = "A",
                competitor2 = "B",
                time = 1000L,
                isFav = false
            ),
            EventDomain(
                id = "${id}_2",
                sportId = id,
                competitor1 = "C",
                competitor2 = "D",
                time = 2000L,
                isFav = true
            )
        )
    )
}