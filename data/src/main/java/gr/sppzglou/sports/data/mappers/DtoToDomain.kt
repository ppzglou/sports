package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.remote.dto.EventDto
import gr.sppzglou.sports.data.remote.dto.SportDto
import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain

fun SportDto.toDomain() = SportDomain(
    id = id,
    name = name,
    events = events.map { it.toDomain() },
)

fun EventDto.toDomain() = EventDomain(
    id = id,
    sportId = sportId,
    competitor1 = competitor.split("-")[0],
    competitor2 = competitor.split("-")[1],
    time = time,
    isFav = false
)