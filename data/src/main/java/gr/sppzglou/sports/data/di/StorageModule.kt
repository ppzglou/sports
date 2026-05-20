package gr.sppzglou.sports.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.sppzglou.sports.data.local.db.AppDB
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun provideUsersDB(
        @ApplicationContext context: Context,
    ): AppDB = AppDB.getInstance(context)

}
