package ru.atruskova.koshelek.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.atruskova.koshelek.App
import ru.atruskova.koshelek.data.database.AppDatabase.Companion.DB_VERSION
import ru.atruskova.koshelek.data.database.daos.BreedDao
import ru.atruskova.koshelek.data.database.daos.PhotoDao
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.data.database.entity.PhotoEntity

@Database(
    entities = [
        BreedEntity::class,
        PhotoEntity::class
    ],
    exportSchema = false,
    version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getBreedDao(): BreedDao
    abstract fun getPhotoDao(): PhotoDao

    companion object {
        const val DB_VERSION = 1
        private const val DB_NAME = "koshelek_db"

        @Volatile
        private var INSTANCE: AppDatabase? = null


        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        fun getBreedDao(): BreedDao = getInstance(App.instance).getBreedDao()
        fun getPhotoDao(): PhotoDao = getInstance(App.instance).getPhotoDao()

        private fun build(context: Context) =
            Room.databaseBuilder(
                context,
                AppDatabase::class.java, DB_NAME
            ).build()
    }
}