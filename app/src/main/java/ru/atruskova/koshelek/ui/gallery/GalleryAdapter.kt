package ru.atruskova.koshelek.ui.gallery

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.database.entity.PhotoEntity
import ru.atruskova.koshelek.databinding.ItemGalleryBinding
import ru.atruskova.koshelek.helper.util.ImageUtil.loadImage
import ru.atruskova.koshelek.helper.views.ListBindingAdapter
import ru.atruskova.koshelek.helper.views.ViewHolder
import java.lang.Exception

class GalleryAdapter(
    private val handler: AlbumListHandler
) : ListBindingAdapter<String, ItemGalleryBinding>(
    diffCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.equals(newItem)
        }

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemGalleryBinding {
        return DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_gallery,
                parent,
                false
            )
    }

    override fun bind(
        binding: ItemGalleryBinding,
        item: String
    ) {
        binding.path = item
        // refactor me
        val imageView = binding.root.findViewById<ImageView>(R.id.image)
        try {
            handler.onLoadingStarted()
            Glide.with(imageView)
                .load(item)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        handler.onLoadFailed(e?.origin)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        handler.onLoadEnd()
                        return false
                    }
                })
                .centerCrop()
                .into(imageView)

        } catch (e: Exception) {
            imageView.setImageResource(R.drawable.ic_home_black_24dp)
        }

    }

}