package gr.sppzglou.sports.presentation.screens.dash.vm

import dagger.hilt.android.lifecycle.HiltViewModel
import gr.sppzglou.sports.domain.FailureWrapper
import gr.sppzglou.sports.domain.cases.FetchDataUC
import gr.sppzglou.sports.presentation.screens.base.BaseVM
import gr.sppzglou.sports.presentation.screens.base.EmptyUiData
import gr.sppzglou.sports.presentation.screens.base.EmptyUiState
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    private val fetchDataUC: FetchDataUC
) : BaseVM<
        EmptyUiState,
        EmptyUiData,
        DashboardEffect
        >(
    initialState = EmptyUiState()
) {

    init {
        fetchData()
    }

    private fun fetchData() = launch {
        fetchDataUC()
    }

    override fun updateData(transform: EmptyUiData.() -> EmptyUiData) = Unit
    override fun setError(error: FailureWrapper) = Unit
    override fun switchLoading(bool: Boolean) = Unit
}