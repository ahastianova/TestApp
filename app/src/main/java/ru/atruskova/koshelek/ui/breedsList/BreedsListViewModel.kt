package ru.atruskova.koshelek.ui.breedsList

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.atruskova.koshelek.data.models.BreedWithCounter
import ru.atruskova.koshelek.data.repos.BreedRepository
import ru.atruskova.koshelek.helper.views.Common
import ru.atruskova.koshelek.helper.views.Screen
import ru.atruskova.koshelek.ui.base.BaseViewModel

class BreedsListViewModel : BaseViewModel(),
    BreedListHandler {
    private val repo = BreedRepository()

    val breedsList: LiveData<List<BreedWithCounter>> = repo.getAllBreeds()

    init {
        launch {
            repo.updateBreedsFromServer()
        }
    }

    override fun onBreedClick(breed: BreedWithCounter) {
        val screen = if (breed.count > 0) Screen.SUBBREEDS else Screen.GALLERY
        _navigateToFragment.value =
            Pair(screen, listOf(Pair(Common.BREED_NAME, breed.breed.name)))

    }
}