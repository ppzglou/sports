package gr.sppzglou.sports.data

import gr.sppzglou.sports.data.local.LocalDataSource
import gr.sppzglou.sports.data.mappers.toDomain
import gr.sppzglou.sports.data.mappers.toEntity
import gr.sppzglou.sports.data.remote.RemoteDataSource
import gr.sppzglou.sports.domain.Repository
import gr.sppzglou.sports.domain.ResultWrapper
import gr.sppzglou.sports.domain.models.SportDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) : Repository, RepositoryExt() {

    override suspend fun fetchAll(): ResultWrapper<Unit> = remote.fetchSports()
        .onSuccessUnit {
            val entities = it.map { it.toDomain().toEntity() }
            local.insertSports(entities.map { it.first })
            local.insertEvents(entities.flatMap { it.second })
        }

    override suspend fun updateFavorites(eventIds: List<String>, isFav: Boolean) {
        local.updateFavorite(eventIds, isFav)
    }

    override fun getSports(): Flow<List<SportDomain>> =
        local.getSports().map {
            it.map { it.toDomain() }
        }


}