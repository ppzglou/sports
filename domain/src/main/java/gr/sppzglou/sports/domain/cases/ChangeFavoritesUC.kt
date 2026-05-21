package gr.sppzglou.sports.domain.cases

import gr.sppzglou.sports.domain.Repository
import javax.inject.Inject

class ChangeFavoritesUC @Inject constructor(
    private val repo: Repository,
) {
    suspend operator fun invoke(ids: List<String>, flag: Boolean) =
        repo.updateFavorites(ids, flag)
}