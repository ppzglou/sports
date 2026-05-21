package gr.sppzglou.sports.domain.models

data class EventDomain(
    val id: String,
    val sportId: String,
    val competitor1: String,
    val competitor2: String,
    val time: Long,
    val isFav: Boolean
)