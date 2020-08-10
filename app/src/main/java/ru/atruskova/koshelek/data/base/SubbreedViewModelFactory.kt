package ru.atruskova.koshelek.data.base

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.atruskova.koshelek.ui.subBreedsList.SubbreedsListViewModel

class SubbreedViewModelFactory(private val name: String?) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        return SubbreedsListViewModel(name?:"") as T
    }
}