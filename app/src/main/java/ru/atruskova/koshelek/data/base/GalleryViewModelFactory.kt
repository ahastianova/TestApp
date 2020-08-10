package ru.atruskova.koshelek.data.base

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.atruskova.koshelek.ui.gallery.GalleryViewModel
import ru.atruskova.koshelek.ui.subBreedsList.SubbreedsListViewModel

class GalleryViewModelFactory(
    private val name: String?,
    private val subreedName: String?,
    private val isSubbreed: Boolean?,
    private val isFavoriteMode: Boolean?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(@NonNull modelClass: Class<T>): T {
        return GalleryViewModel(name ?: "", subreedName ?: "", isSubbreed ?: false, isFavoriteMode?:false) as T
    }
}