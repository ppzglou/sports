package gr.sppzglou.sports.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Events")
data class EventEntity(
    @PrimaryKey
    val id: String,
    val sportId: String,
    val competitor1: String,
    val competitor2: String,
    val time: Long,
    val isFav: Boolean
)