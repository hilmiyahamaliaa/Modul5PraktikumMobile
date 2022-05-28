package com.example.fruitsapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitsapp.databinding.ListViewItemBinding
import com.example.fruitsapp.network.Fruit

class FruitListAdapter (val clickListener: FruitListener) :
    ListAdapter<Fruit, FruitListAdapter.FruitViewHolder>(DiffCallback) {

    class FruitViewHolder(
        var binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(clickListener: FruitListener, fruit: Fruit) {
            binding.fruit = fruit
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Fruit>() {

        override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
            return oldItem.origin == newItem.origin && oldItem.largestCountry == newItem.largestCountry
                    && oldItem.productionInBillions == newItem.productionInBillions
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FruitViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        val fruit = getItem(position)
        holder.bind(clickListener, fruit)
    }
}

class FruitListener(val clickListener: (fruit: Fruit) -> Unit) {
    fun onClick(fruit: Fruit) = clickListener(fruit)
}