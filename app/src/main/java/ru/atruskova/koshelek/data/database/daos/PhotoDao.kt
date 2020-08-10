package ru.atruskova.koshelek.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.data.database.entity.PhotoEntity

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<PhotoEntity>)

    @Query("SELECT imagePath FROM Photos WHERE breedName=:breedName")
    fun getPhotos(breedName: String): LiveData<List<String>>

    @Update
    suspend fun update(item: PhotoEntity)

    @Query("SELECT * FROM Photos WHERE imagePath = :path")
    fun getCurrentPhoto(path: String) : LiveData<PhotoEntity>

    @Query("SELECT imagePath FROM Photos WHERE isFavorite=1 AND breedName=:breadName")
    fun getFavoritePhotos(breadName: String): LiveData<List<String>>
}