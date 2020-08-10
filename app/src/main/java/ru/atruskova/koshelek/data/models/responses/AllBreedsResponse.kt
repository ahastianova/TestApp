package ru.atruskova.koshelek.data.models.responses

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class AllBreedsResponse (
    @SerializedName("message")
    val message: Map<String, List<String>>
)