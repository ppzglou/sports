package gr.sppzglou.sports.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SportWithEvents(
    @Embedded
    val sport: SportEntity,
    @Relation(
        entity = EventEntity::class,
        parentColumn = "id",
        entityColumn = "sportId"
    )
    val events: List<EventEntity>
)