package ru.atruskova.koshelek.helper.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.helper.util.ImageUtil.loadImage

object BindingAdapters {
    @BindingAdapter("getImage")
    @JvmStatic
    fun getImage(imageView: ImageView, path: String?) {
        imageView.loadImage(path)
    }
}