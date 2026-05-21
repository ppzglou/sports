package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository
import javax.inject.Inject

class SwitchFavoriteUC @Inject constructor(
    private val repo: Repository,
) {
    suspend operator fun invoke(id: String) =
        repo.updateFavorite(id)
}