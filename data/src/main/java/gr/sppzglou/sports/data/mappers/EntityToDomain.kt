package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportWithEvents
import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain

fun SportWithEvents.toDomain() = SportDomain(
    id = sport.id,
    name = sport.name,
    events = events.map { it.toDomain() },
)

fun EventEntity.toDomain() = EventDomain(
    id = id,
    sportId = sportId,
    competitor1 = competitor1,
    competitor2 = competitor2,
    time = time,
    isFav = isFav
)