package ru.atruskova.koshelek.helper.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import ru.atruskova.koshelek.R
import java.lang.Exception

object ImageUtil {
    fun ImageView.loadImage(
        path: String?) {
        try {
        Glide.with(this)
            .load(path)
            .centerCrop()
//            .placeholder()
            .into(this)
    } catch (e: Exception)
    {
        this.setImageResource(R.drawable.ic_home_black_24dp)
    }
}
}