package gr.sppzglou.sports.domain

import gr.sppzglou.sports.domain.models.SportDomain
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun fetchAll(): ResultWrapper<Unit>

    suspend fun updateFavorites(eventIds: List<String>, isFav: Boolean): Unit

    fun getSports(): Flow<List<SportDomain>>

}