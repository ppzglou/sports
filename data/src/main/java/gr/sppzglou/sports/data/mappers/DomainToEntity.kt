package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportEntity
import gr.sppzglou.sports.domain.models.SportDomain

fun SportDomain.toEntity(): Pair<SportEntity, List<EventEntity>> = Pair(
    SportEntity(
        id = id,
        name = name,
    ),
    events.map {
        EventEntity(
            id = it.id,
            sportId = it.sportId,
            competitor1 = it.competitor1,
            competitor2 = it.competitor2,
            time = it.time,
            isFav = it.isFav
        )
    }
)