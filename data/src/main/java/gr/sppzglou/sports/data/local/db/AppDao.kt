package gr.sppzglou.sports.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportEntity
import gr.sppzglou.sports.data.local.entities.SportWithEvents
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSports(sports: List<SportEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(events: List<EventEntity>)

    @Query("UPDATE Events SET isFav = :isFav WHERE id IN (:eventIds)")
    suspend fun updateFavorite(eventIds: List<String>, isFav: Boolean)

    @Query("SELECT * FROM Sports")
    fun getSportsWithEvents(): Flow<List<SportWithEvents>>
}
