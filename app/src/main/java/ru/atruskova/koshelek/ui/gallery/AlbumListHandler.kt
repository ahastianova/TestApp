package ru.atruskova.koshelek.ui.gallery

interface AlbumListHandler {
    fun onLoadingStarted()

    fun onLoadEnd()

    fun onLoadFailed(e: Exception?)
}