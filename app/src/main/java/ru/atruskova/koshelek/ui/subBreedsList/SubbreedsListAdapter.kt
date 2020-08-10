package ru.atruskova.koshelek.ui.subBreedsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import ru.atruskova.koshelek.R
import ru.atruskova.koshelek.data.database.entity.BreedEntity
import ru.atruskova.koshelek.databinding.ItemSubbreedBinding
import ru.atruskova.koshelek.helper.views.ListBindingAdapter

class SubbreedsListAdapter(
    private val handler: SubbreedListHandler
) : ListBindingAdapter<BreedEntity, ItemSubbreedBinding>(
    diffCallback = object : DiffUtil.ItemCallback<BreedEntity>() {
        override fun areContentsTheSame(oldItem: BreedEntity, newItem: BreedEntity): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areItemsTheSame(oldItem: BreedEntity, newItem: BreedEntity): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemSubbreedBinding {
        return DataBindingUtil
            .inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_subbreed,
                parent,
                false
            )
    }

    override fun bind(binding: ItemSubbreedBinding, item: BreedEntity) {
        binding.subbreed = item
        binding.handler = handler

    }
}