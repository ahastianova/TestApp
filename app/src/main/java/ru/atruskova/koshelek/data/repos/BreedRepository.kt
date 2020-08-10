package ru.atruskova.koshelek.data.repos

import androidx.lifecycle.LiveData
import ru.atruskova.koshelek.App
import ru.atruskova.koshelek.data.database.AppDatabase
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.data.models.BreedWithCounter

class BreedRepository {
    private val breedDao = AppDatabase.getBreedDao()
    private val networkManager = App.networkManager

    suspend fun updateBreedsFromServer() {
        val receivedItems : List<BreedEntity> = networkManager.getAllBreedsList().flatMap {
            BreedEntity.fromBreed(it)
        }

        if (receivedItems.isNotEmpty())
            breedDao.insertAll(receivedItems)
    }

    fun getAllBreeds(): LiveData<List<BreedWithCounter>> {
        return breedDao.getBreedsWithSubCount()
    }

    fun getSubbreeds(breed: String) : LiveData<List<BreedEntity>> {
        return breedDao.getSubbreeds(breed)
    }

    fun getFavorites() : LiveData<List<BreedWithCounter>> {
        return breedDao.getFavoritesWithCount()
    }

}