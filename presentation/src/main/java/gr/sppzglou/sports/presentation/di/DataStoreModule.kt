package gr.sppzglou.sports.presentation.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.sppzglou.sports.presentation.utils.PrefsDataStore
import gr.sppzglou.sports.presentation.utils.createDataStoreSingleton
import gr.sppzglou.sports.presentation.utils.dataStoreFileName
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providesPrefsDataStore(
        @ApplicationContext applicationContext: Context,
    ): PrefsDataStore = createDataStoreSingleton(
        producePath = { applicationContext.filesDir.resolve(dataStoreFileName).absolutePath },
    )
}