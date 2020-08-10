package ru.atruskova.koshelek.data.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.data.models.BreedWithCounter

@Dao
interface BreedDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(list: List<BreedEntity>)

    @Query(
        "SELECT * FROM Breeds AS a " +
                "LEFT JOIN (SELECT superBreed AS sb, COUNT(name) AS count FROM Breeds GROUP BY superBreed) AS b " +
                "ON a.name = b.sb " +
                "WHERE superBreed IS NULL ORDER BY name"
    )
    fun getBreedsWithSubCount(): LiveData<List<BreedWithCounter>>

    @Query("SELECT * FROM Breeds WHERE superBreed = :breed")
    fun getSubbreeds(breed: String) : LiveData<List<BreedEntity>>

    @Query(
        "SELECT * FROM Breeds AS a " +
                "LEFT JOIN (SELECT breedName AS sb, COUNT(imagePath) AS count FROM Photos WHERE isFavorite=1 GROUP BY breedName) AS b " +
                "ON a.name = b.sb " +
                "WHERE COUNT>0 ORDER BY name"
    )
    fun getFavoritesWithCount() : LiveData<List<BreedWithCounter>>
}