package ru.atruskova.koshelek.ui.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.helper.util.ApiException
import ru.atruskova.koshelek.helper.util.ResourceHelper
import ru.atruskova.koshelek.helper.views.Screen
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    protected val _errorLiveData = SingleLiveEvent<String>()
    val errorLiveData: LiveData<String> = _errorLiveData
    protected val _isLoadingLiveData = SingleLiveEvent<Boolean>()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData
    protected val _navigateToFragment = SingleLiveEvent<Pair<Screen, List<Pair<String, String>>>>()
    val navigateToFragment: LiveData<Pair<Screen, List<Pair<String, String>>>> = _navigateToFragment

    private val job = SupervisorJob()
    private val handler = CoroutineExceptionHandler { _, exception ->
        exception.printStackTrace()
        _errorLiveData.postValue(
            ResourceHelper.getStringResource(R.string.api_exception_error)
        )

    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job + handler

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }

    protected fun loading(isLoading: Boolean, async: Boolean = true) {
        if (async) {
            _isLoadingLiveData.postValue(isLoading)
        } else {
            _isLoadingLiveData.value = isLoading
        }
    }

    protected fun onError(message: String?) {
        _errorLiveData.value =
            message ?: ResourceHelper.getStringResource(R.string.api_exception_error)
    }

}