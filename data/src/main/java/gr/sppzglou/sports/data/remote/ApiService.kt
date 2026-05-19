package gr.sppzglou.sports.data.remote

import gr.sppzglou.sports.data.remote.dto.SportDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("sports.json")
    suspend fun fetchSports(): Response<List<SportDto>>
}