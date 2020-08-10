package ru.atruskova.koshelek.ui.breedsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.models.BreedWithCounter
import ru.atruskova.koshelek.databinding.ItemBreedBinding
import ru.atruskova.koshelek.helper.views.ListBindingAdapter

class BreedsListAdapter (private val handler: BreedListHandler) : ListBindingAdapter<BreedWithCounter, ItemBreedBinding>(
    diffCallback = object : DiffUtil.ItemCallback<BreedWithCounter>() {
        override fun areContentsTheSame(oldItem: BreedWithCounter, newItem: BreedWithCounter): Boolean {
            return oldItem.breed.name == newItem.breed.name
        }

        override fun areItemsTheSame(oldItem: BreedWithCounter, newItem: BreedWithCounter): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemBreedBinding {
        return DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_breed,
                parent,
                false
            )
    }

    override fun bind(binding: ItemBreedBinding, item: BreedWithCounter) {
        binding.breedModel = item
        binding.subcountVisibility = if (item.count == 0) View.GONE else View.VISIBLE
        binding.handler = handler
    }


}
