package gr.sppzglou.sports.domain.models

data class SportDomain(
    val id: String,
    val name: String,
    val events: List<EventDomain>
)