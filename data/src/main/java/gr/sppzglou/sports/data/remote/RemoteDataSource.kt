package gr.sppzglou.sports.data.remote

import gr.sppzglou.sports.data.remote.dto.SportDto
import gr.sppzglou.sports.domain.ResultWrapper
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {

    suspend fun fetchSports(): ResultWrapper<List<SportDto>> = safeCall {
        api.fetchSports()
    }
}