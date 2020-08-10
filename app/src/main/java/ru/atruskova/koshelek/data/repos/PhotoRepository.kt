package ru.atruskova.koshelek.data.repos

import androidx.lifecycle.LiveData
import ru.atruskova.koshelek.App
import ru.atruskova.koshelek.data.database.AppDatabase
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.data.database.entity.PhotoEntity

class PhotoRepository(
    private val breedName: String,
    private val subbreadName: String,
    private val isSubreed: Boolean,
    private val isFavoriteMode: Boolean
) {
    private val photoDao = AppDatabase.getPhotoDao()
    private val networkManager = App.networkManager

    suspend fun updatePhotosFromServer() {
        val receivedItems: List<PhotoEntity> = if (isSubreed)
            networkManager.getBreedPathsList(breedName, subbreadName).map {
                PhotoEntity.from(it, subbreadName)
            }
        else
            networkManager.getBreedPathsList(breedName).map {
                PhotoEntity.from(it, breedName)
            }

        if (receivedItems.isNotEmpty())
            photoDao.insertAll(receivedItems)
    }

    fun getPaths(): LiveData<List<String>> {
        return if (isFavoriteMode)
            photoDao.getFavoritePhotos(breedName)
        else if (isSubreed)
            photoDao.getPhotos(subbreadName)
        else
            photoDao.getPhotos(breedName)
    }


    fun getSelectedPhotoState(path: String): LiveData<PhotoEntity> {
        return photoDao.getCurrentPhoto(path)
    }

    suspend fun updateFavoriteState(item: PhotoEntity) {
        photoDao.update(
            item.copy(
                isFavorite = !item.isFavorite
            )
        )
    }

}