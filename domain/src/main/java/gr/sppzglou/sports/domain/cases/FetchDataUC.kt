package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository

class FetchDataUC(
    private val repo: Repository,
) {
    suspend operator fun invoke() = repo.fetchAll()
}