package ru.atruskova.koshelek.ui.subBreedsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.data.repos.BreedRepository
import ru.atruskova.koshelek.helper.views.Common
import ru.atruskova.koshelek.helper.views.Screen
import ru.atruskova.koshelek.ui.base.BaseViewModel

class SubbreedsListViewModel(
    private val breedName: String
) : BaseViewModel(), SubbreedListHandler {
    val repo = BreedRepository()

    val subbreedsList: LiveData<List<BreedEntity>> = repo.getSubbreeds(breedName)

    override fun onClick(breedEntity: BreedEntity) {
        val screen = Screen.GALLERY
        _navigateToFragment.value =
            Pair(screen, listOf(Pair(Common.SUBBREED_NAME, breedEntity.name), Pair(Common.BREED_NAME, breedName)))

    }
}