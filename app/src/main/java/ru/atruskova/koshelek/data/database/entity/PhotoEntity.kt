package ru.atruskova.koshelek.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "Photos"
)
data class PhotoEntity(
    @PrimaryKey
    val imagePath: String,
    val breedName: String,
    val isFavorite: Boolean = false
) {
    companion object {
        fun from(path: String, breedName: String) : PhotoEntity {
            return PhotoEntity(path, breedName)
        }
    }
}