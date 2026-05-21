package gr.sppzglou.sports.domain

import gr.sppzglou.sports.domain.models.SportDomain
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun fetchAll(): ResultWrapper<Unit>

    suspend fun updateFavorite(eventId: String): Unit

    fun getSports(sportFavIds: List<String>): Flow<List<SportDomain>>

}