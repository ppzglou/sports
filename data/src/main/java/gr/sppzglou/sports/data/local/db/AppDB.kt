package gr.sppzglou.sports.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gr.sppzglou.sports.data.local.entities.EventEntity
import gr.sppzglou.sports.data.local.entities.SportEntity

@Database(
    entities = [
        SportEntity::class,
        EventEntity::class
    ],
    version = 1,
    exportSchema = false,
)

abstract class AppDB : RoomDatabase() {
    abstract fun dao(): AppDao

    companion object {
        @Volatile
        private var instance: AppDB? = null

        fun getInstance(context: Context): AppDB =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context): AppDB =
            Room
                .databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "appDB",
                ).fallbackToDestructiveMigration(false)
                .build()
    }
}
