package ru.atruskova.koshelek.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlinx.coroutines.launch
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.database.entity.PhotoEntity
import ru.atruskova.koshelek.data.repos.PhotoRepository
import ru.atruskova.koshelek.helper.util.ResourceHelper
import ru.atruskova.koshelek.ui.base.BaseViewModel
import java.lang.Exception

class GalleryViewModel(
    breedName: String,
    subbreedName: String,
    isSubbreed: Boolean,
    isFavorite: Boolean
) : BaseViewModel(), AlbumListHandler {
    private val repo = PhotoRepository(breedName, subbreedName, isSubbreed, isFavorite)
    val pathsList: LiveData<List<String>> = repo.getPaths()
    private val _currentPath: MutableLiveData<String> = MutableLiveData()
    val currentPath: LiveData<String> = _currentPath
    val currentPhoto: LiveData<PhotoEntity> = Transformations.switchMap(currentPath) {
        repo.getSelectedPhotoState(it)
    }

    init {
        launch {
            repo.updatePhotosFromServer()
        }
    }

    override fun onLoadEnd() {
        loading(false)
    }

    override fun onLoadingStarted() {
        loading(true)
    }

    override fun onLoadFailed(e: Exception?) {
        loading(false)
        onError(ResourceHelper.getStringResource(R.string.api_exception_error))
    }

    fun setCurrentPhoto(item: String) {
        _currentPath.postValue(item)
    }

    fun getCurrentPhotoPath() : String? {
        return currentPhoto.value?.imagePath
    }

    fun onLikeClick() {
        // ref
        currentPhoto.value?.let {
            launch {
                repo.updateFavoriteState(it)
            }
        }

    }
}