package ru.atruskova.koshelek.data.models

import androidx.room.Embedded
import ru.atruskova.koshelek.data.database.entity.BreedEntity

data class BreedWithCounter (
    @Embedded
    val breed: BreedEntity,
    val count: Int
)