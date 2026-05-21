package gr.sppzglou.sports.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportEntity
import gr.sppzglou.sports.data.local.entities.SportEventRow
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSports(sports: List<SportEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    @Query("UPDATE Events SET isFav = NOT isFav WHERE id = :eventId")
    suspend fun updateFavorite(eventId: String)

    @Query(
        """
    SELECT 
        s.id AS sportId,
        s.name AS sportName,

        e.id AS eventId,
        e.sportId AS eventSportId,
        e.competitor1 AS competitor1,
        e.competitor2 AS competitor2,
        e.time AS time,
        e.isFav AS isFav
    FROM Sports s
    LEFT JOIN Events e 
        ON e.sportId = s.id
        AND (
            s.id NOT IN (:favoriteFilteredSportIds)
            OR e.isFav = 1
        )
    """
    )
    fun getSportsWithFilteredEvents(
        favoriteFilteredSportIds: List<String>
    ): Flow<List<SportEventRow>>
}
