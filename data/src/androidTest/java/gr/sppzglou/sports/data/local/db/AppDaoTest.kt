package gr.sppzglou.sports.data.local.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AppDaoTest {

    private lateinit var db: AppDB
    private lateinit var dao: AppDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDB::class.java
        )
            .allowMainThreadQueries()
            .build()

        dao = db.dao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun getSportsWithFilteredEvents_withoutFilters_returnsAllSportsAndEvents() = runTest {
        insertSampleData()

        val result = dao.getSportsWithFilteredEvents(emptyList()).first()

        assertEquals(4, result.size)

        val footballEvents = result.filter { it.sportId == "FOOT" }
        assertEquals(2, footballEvents.size)

        val basketEvents = result.filter { it.sportId == "BASK" }
        assertEquals(1, basketEvents.size)

        val tennisRows = result.filter { it.sportId == "TENN" }
        assertEquals(1, tennisRows.size)
        assertNull(tennisRows.first().eventId)
    }

    @Test
    fun getSportsWithFilteredEvents_filteredSport_returnsOnlyFavoriteEvents() = runTest {
        insertSampleData()

        val result = dao.getSportsWithFilteredEvents(listOf("FOOT")).first()

        val footballEvents = result.filter { it.sportId == "FOOT" }

        assertEquals(1, footballEvents.size)
        assertEquals("event_2", footballEvents.first().eventId)
        assertTrue(footballEvents.first().isFav == true)
    }

    @Test
    fun getSportsWithFilteredEvents_filteredSportWithNoFavorites_returnsSportWithNullEvent() =
        runTest {
            insertSampleData()

            val result = dao.getSportsWithFilteredEvents(listOf("BASK")).first()

            val basketRows = result.filter { it.sportId == "BASK" }

            assertEquals(1, basketRows.size)
            assertEquals("BASK", basketRows.first().sportId)
            assertNull(basketRows.first().eventId)
        }

    @Test
    fun updateFavorite_togglesFalseToTrue() = runTest {
        insertSampleData()

        dao.updateFavorite("event_1")

        val result = dao.getSportsWithFilteredEvents(listOf("FOOT")).first()
        val event = result.first { it.eventId == "event_1" }

        assertTrue(event.isFav == true)
    }

    @Test
    fun updateFavorite_togglesTrueToFalse() = runTest {
        insertSampleData()

        dao.updateFavorite("event_2")

        val result = dao.getSportsWithFilteredEvents(listOf("FOOT")).first()
        val footballRows = result.filter { it.sportId == "FOOT" }

        assertTrue(footballRows.all { it.eventId != "event_2" })
    }

    private suspend fun insertSampleData() {
        dao.insertSports(
            listOf(
                SportEntity(id = "FOOT", name = "SOCCER"),
                SportEntity(id = "BASK", name = "BASKETBALL"),
                SportEntity(id = "TENN", name = "TENNIS"),
            )
        )

        dao.insertEvents(
            listOf(
                EventEntity(
                    id = "event_1",
                    sportId = "FOOT",
                    competitor1 = "Barcelona",
                    competitor2 = "Real Madrid",
                    time = 1000L,
                    isFav = false
                ),
                EventEntity(
                    id = "event_2",
                    sportId = "FOOT",
                    competitor1 = "Liverpool",
                    competitor2 = "Arsenal",
                    time = 2000L,
                    isFav = true
                ),
                EventEntity(
                    id = "event_3",
                    sportId = "BASK",
                    competitor1 = "Lakers",
                    competitor2 = "Bulls",
                    time = 3000L,
                    isFav = false
                )
            )
        )
    }
}