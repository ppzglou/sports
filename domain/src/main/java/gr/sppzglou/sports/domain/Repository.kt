package gr.sppzglou.sports.domain


interface Repository {

    suspend fun fetchAll(): ResultWrapper<Unit>

    suspend fun updateFavorite(sportId: String, isFav: Boolean): Unit
}