package gr.sppzglou.sports.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Sports")
data class SportEntity(
    @PrimaryKey
    val id: String,
    val name: String
)