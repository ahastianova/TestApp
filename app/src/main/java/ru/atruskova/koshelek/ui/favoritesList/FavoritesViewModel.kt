package ru.atruskova.koshelek.ui.favoritesList

import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import ru.atruskova.koshelek.data.models.BreedWithCounter
import ru.atruskova.koshelek.data.repos.BreedRepository
import ru.atruskova.koshelek.helper.views.Common
import ru.atruskova.koshelek.helper.views.Screen
import ru.atruskova.koshelek.ui.base.BaseViewModel
import ru.atruskova.koshelek.ui.breedsList.BreedListHandler

class FavoritesViewModel : BaseViewModel(),
    BreedListHandler {
    private val repo = BreedRepository()

    val breedsList: LiveData<List<BreedWithCounter>> = repo.getFavorites()

    override fun onBreedClick(breedModel: BreedWithCounter) {
        val screen = Screen.GALLERY
        _navigateToFragment.value = Pair(
            screen,
            listOf(
                Pair(Common.IS_FAVORITE_MODE, "1"),
                Pair(Common.BREED_NAME, breedModel.breed.name)
            )
        )

    }
}