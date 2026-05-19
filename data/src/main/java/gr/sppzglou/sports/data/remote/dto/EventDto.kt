package gr.sppzglou.sports.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("i")
    val id: String,
    @SerializedName("si")
    val sportId: String,
    @SerializedName("d")
    val competitor: String,
    @SerializedName("tt")
    val time: Long
)