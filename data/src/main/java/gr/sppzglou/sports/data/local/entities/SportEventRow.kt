package gr.sppzglou.sports.data.local.entities

data class SportEventRow(
    val sportId: String,
    val sportName: String,

    val eventId: String?,
    val eventSportId: String?,
    val competitor1: String?,
    val competitor2: String?,
    val time: Long?,
    val isFav: Boolean?,
)