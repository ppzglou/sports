package gr.sppzglou.sports.domain


interface Repository {

    suspend fun fetchAll()

}