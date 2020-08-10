package ru.atruskova.koshelek.helper.util

import java.io.IOException


object ErrorUtil {
    const val ERROR_NO_NETWORK = "1"
    const val ERROR_NULL_BODY = "2"
    const val ERROR_UPDATE_CONTENT = "3"
}

class ApiException(private val error: String) : IOException() {
    override val message: String?
        get() = error
}