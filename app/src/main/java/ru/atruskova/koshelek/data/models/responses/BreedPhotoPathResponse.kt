package ru.atruskova.koshelek.data.models.responses

import com.google.gson.annotations.SerializedName

data class BreedPhotoPathResponse (
    @SerializedName("message")
    val message: List<String>
)