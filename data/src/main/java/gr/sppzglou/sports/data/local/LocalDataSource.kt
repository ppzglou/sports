package gr.sppzglou.sports.data.local

import gr.sppzglou.sports.data.local.db.AppDB
import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportEntity
import javax.inject.Inject

class LocalDataSource
@Inject constructor(
    private val db: AppDB
) {

    suspend fun insertSports(sports: List<SportEntity>) =
        db.dao().insertSports(sports)

    suspend fun insertEvents(events: List<EventEntity>) =
        db.dao().insertEvents(events)

    suspend fun updateFavorite(eventId: String) =
        db.dao().updateFavorite(eventId)

    fun getSports(sportFavIds: List<String>) =
        db.dao().getSportsWithFilteredEvents(sportFavIds)
}