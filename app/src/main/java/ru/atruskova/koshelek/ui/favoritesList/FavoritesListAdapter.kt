package ru.atruskova.koshelek.ui.favoritesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.models.BreedWithCounter
import ru.atruskova.koshelek.databinding.ItemBreedBinding
import ru.atruskova.koshelek.databinding.ItemFavoriteBinding
import ru.atruskova.koshelek.helper.views.ListBindingAdapter
import ru.atruskova.koshelek.ui.breedsList.BreedListHandler

class FavoritesListAdapter (private val handler: BreedListHandler) : ListBindingAdapter<BreedWithCounter, ItemFavoriteBinding>(
    diffCallback = object : DiffUtil.ItemCallback<BreedWithCounter>() {
        override fun areContentsTheSame(oldItem: BreedWithCounter, newItem: BreedWithCounter): Boolean {
            return oldItem.breed.name == newItem.breed.name
        }

        override fun areItemsTheSame(oldItem: BreedWithCounter, newItem: BreedWithCounter): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemFavoriteBinding {
        return DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_favorite,
                parent,
                false
            )
    }

    override fun bind(binding: ItemFavoriteBinding, item: BreedWithCounter) {
        binding.breedModel = item
        binding.handler = handler
    }


}
