package ru.atruskova.koshelek.data.network

import android.content.Context
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.models.Breed
import ru.atruskova.koshelek.helper.network.ApiResponseMapper

class NetworkManager(
    private val api: Api
) {
    private val apiResponseMapper = ApiResponseMapper()

    suspend fun getAllBreedsList(): List<Breed> {
        return getResponse(api.getAllBreeds()).message.map {
            Breed(it.key, it.value)
        }
    }

    suspend fun getBreedPathsList(breedName: String): List<String> {
        return getResponse(api.getBreedPhotosPath(breedName)).message
    }

    suspend fun getBreedPathsList(breedName: String, subbreadName: String): List<String> {
        return getResponse(api.getBreedPhotosPath(breedName)).message
    }

    private fun <R> getResponse(response: Response<R>): R {
        return apiResponseMapper.map(response)
    }

    companion object {
        fun create(appContext: Context): NetworkManager {
            return NetworkManager(
                Retrofit.Builder()
                    .baseUrl(appContext.getString(R.string.base_url))
                    .addConverterFactory(
                        GsonConverterFactory.create())
                    .build()
                    .create(Api::class.java)
            )
        }
    }

}