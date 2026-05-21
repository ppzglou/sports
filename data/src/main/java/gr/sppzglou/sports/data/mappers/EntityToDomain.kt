package gr.sppzglou.sports.data.mappers

import gr.sppzglou.sports.data.local.entities.SportEventRow
import gr.sppzglou.sports.domain.models.EventDomain
import gr.sppzglou.sports.domain.models.SportDomain


fun List<SportEventRow>.toDomain(): List<SportDomain> {
    return groupBy { it.sportId }
        .map { (_, rows) ->

            val first = rows.first()

            SportDomain(
                id = first.sportId,
                name = first.sportName,
                events = rows
                    .filter { it.eventId != null }
                    .map {
                        EventDomain(
                            id = it.eventId!!,
                            sportId = it.eventSportId!!,
                            competitor1 = it.competitor1!!,
                            competitor2 = it.competitor2!!,
                            time = it.time!!,
                            isFav = it.isFav!!
                        )
                    }
            )
        }
}