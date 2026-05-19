package gr.sppzglou.sports.data.remote.dto

import com.google.gson.annotations.SerializedName

data class SportDto(
    @SerializedName("i")
    val id: String,
    @SerializedName("d")
    val name: String,
    @SerializedName("e")
    var events: List<EventDto>
)