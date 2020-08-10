package ru.atruskova.koshelek.helper.network

import retrofit2.Response
import ru.atruskova.koshelek.helper.util.ApiException
import ru.atruskova.koshelek.helper.util.ErrorUtil

class ApiResponseMapper {

    fun <R> map(response: Response<R>): R {
        if (response.isSuccessful) {
            return response.body() ?: throw ApiException(ErrorUtil.ERROR_NULL_BODY)
        } else {
            throw ApiException(response.code().toString())
        }
    }
}