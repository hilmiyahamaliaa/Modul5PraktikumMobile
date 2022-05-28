package com.example.fruitsapp

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitsapp.network.Fruit
import com.example.fruitsapp.ui.FruitApiStatus
import com.example.fruitsapp.ui.FruitListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Fruit>?) {
    val adapter = recyclerView.adapter as FruitListAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: FruitApiStatus?) {
    when(status) {
        FruitApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FruitApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        FruitApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}