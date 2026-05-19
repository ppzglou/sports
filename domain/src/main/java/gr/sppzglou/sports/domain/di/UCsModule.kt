package gr.sppzglou.sports.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import gr.sppzglou.sports.domain.Repository
import gr.sppzglou.sports.domain.cases.FetchDataUC

@Module
@InstallIn(ViewModelComponent::class)
object UCsModule {

    @Provides
    fun provideFetchDataUC(repository: Repository) = FetchDataUC(repository)

}
