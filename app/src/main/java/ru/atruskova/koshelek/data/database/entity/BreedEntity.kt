package ru.atruskova.koshelek.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.atruskova.koshelek.data.models.Breed

@Entity (
    tableName = "Breeds"
)
data class BreedEntity(
    @PrimaryKey
    val name: String,
    val superBreed: String?
) {
    companion object {
        fun fromBreed(from: Breed): List<BreedEntity> {
            val result = mutableListOf<BreedEntity>()
            val superBreedName = from.name ?: ""
            // let's add super breed to list first
            result.add(
                BreedEntity(
                    superBreedName, null
                )
            )
            from.subBreeds?.let { subbreeds ->
                for (breed: String in subbreeds) {
                    result.add(
                        BreedEntity(
                            breed, superBreedName
                        )
                    )
                }
            }

            return result

        }
    }
}