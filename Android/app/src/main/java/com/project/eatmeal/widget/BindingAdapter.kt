package com.project.eatmeal.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.adapter.TodayMealAdapter
import com.project.eatmeal.data.response.Food


@BindingAdapter("recyclerItems")
fun RecyclerView.setRecyclerViewItems(items: ArrayList<Food>?) {
    if (adapter == null) {
        this.adapter = TodayMealAdapter()
        this.layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as? TodayMealAdapter)?.setItem(it) }
}