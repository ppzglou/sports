package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository
import javax.inject.Inject

class FetchDataUC @Inject constructor(
    private val repo: Repository,
) {
    suspend operator fun invoke() = repo.fetchAll()
}