package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository
import javax.inject.Inject

class GetSportsFlowUC @Inject constructor(
    private val repo: Repository,
) {
    operator fun invoke() = repo.getSports()
}