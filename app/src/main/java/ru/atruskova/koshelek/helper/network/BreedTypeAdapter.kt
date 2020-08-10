//package ru.atruskova.koshelek.helper.network
//
//import com.google.gson.*
//import ru.atruskova.koshelek.data.models.Breed
//import java.lang.reflect.Type
//
//
//class BreedTypeAdapter : JsonDeserializer<List<Breed>>{
//    override fun deserialize(
//        json: JsonElement?,
//        typeOfT: Type?,
//        context: JsonDeserializationContext?
//    ): List<Breed> {
//
//        return if ((json as JsonObject)["message"] is JsonObject) {
//            Gson().fromJson(json, ResponseMessage::class.java)
//        } else {
//            Gson().fromJson(json, ResponseString::class.java)
//        }
//    }
//}