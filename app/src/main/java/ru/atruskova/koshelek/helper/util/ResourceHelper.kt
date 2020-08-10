package ru.atruskova.koshelek.helper.util

import androidx.annotation.StringRes
import ru.atruskova.koshelek.App

object ResourceHelper {
    fun getStringResource (@StringRes resId: Int, vararg arg: Any) : String {
        return App.instance.getString(resId, arg)
    }
}