package ru.atruskova.koshelek.helper.views
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class ViewHolder<out T : ViewDataBinding> constructor (val binding: T)
    : RecyclerView.ViewHolder(binding.root)