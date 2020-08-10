package ru.atruskova.koshelek.ui.breedsList

import ru.atruskova.koshelek.data.models.BreedWithCounter

interface BreedListHandler {
    fun onBreedClick(breed: BreedWithCounter)
}