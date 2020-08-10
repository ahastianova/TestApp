package ru.atruskova.koshelek

import android.app.Application
import ru.atruskova.koshelek.data.network.NetworkManager

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        networkManager = NetworkManager.create(instance)

    }

    companion object {
        lateinit var instance: App
            private set

        lateinit var networkManager: NetworkManager
            private set
    }
}