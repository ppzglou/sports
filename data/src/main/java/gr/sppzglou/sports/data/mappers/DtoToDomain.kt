package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.remote.dto.EventDto
import gr.sppzglou.sports.data.remote.dto.SportDto
import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain

fun SportDto.toDomain() = SportDomain(
    id = id,
    name = name.orEmpty(),
    events = (events ?: emptyList()).map { it.toDomain() },
)

fun EventDto.toDomain(): EventDomain {
    val competitors = competitor.orEmpty().split("-")

    return EventDomain(
        id = id,
        sportId = sportId,
        competitor1 = competitors.getOrNull(0).orEmpty(),
        competitor2 = competitors.getOrNull(1).orEmpty(),
        time = time ?: 0L,
        isFav = false
    )
}