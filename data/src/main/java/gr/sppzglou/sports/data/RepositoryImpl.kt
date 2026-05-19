package gr.sppzglou.sports.data

import gr.sppzglou.sports.data.local.LocalDataSource
import gr.sppzglou.sports.data.remote.RemoteDataSource
import gr.sppzglou.sports.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) : Repository {

    override suspend fun fetchAll() {

    }

}