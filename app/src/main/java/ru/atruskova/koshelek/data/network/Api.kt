package ru.atruskova.koshelek.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import ru.atruskova.koshelek.data.models.Breed
import ru.atruskova.koshelek.data.models.responses.AllBreedsResponse
import ru.atruskova.koshelek.data.models.responses.BreedPhotoPathResponse

interface Api {
    @GET("breeds/list/all")
    suspend fun getAllBreeds(): Response<AllBreedsResponse>

    @GET("breed/{breedName}/images")
    suspend fun getBreedPhotosPath(@Path("breedName") breedName: String): Response<BreedPhotoPathResponse>

    @GET("breed/{breedName}/{subbreadName}/images")
    suspend fun getBreedPhotosPath(
        @Path("breedName") breedName: String,
        @Path("subbreadName") subbreadName: String
    ): Response<BreedPhotoPathResponse>
}