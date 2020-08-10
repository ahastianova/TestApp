package ru.atruskova.koshelek.ui.subBreedsList

import ru.atruskova.koshelek.data.database.entity.BreedEntity

interface SubbreedListHandler {
    fun onClick(breedEntity: BreedEntity)
}