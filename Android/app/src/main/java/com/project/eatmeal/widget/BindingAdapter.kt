package com.project.eatmeal.widget

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.adapter.MenuAdapter
import com.project.eatmeal.adapter.TodayMealAdapter
import com.project.eatmeal.data.response.Food


@BindingAdapter("todayMealItems")
fun RecyclerView.setTodayMealItems(items: ArrayList<Food>?) {
    if (adapter == null) {
        this.adapter = TodayMealAdapter()
        this.layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as TodayMealAdapter).setItem(it) }
}

@BindingAdapter("menuItems")
fun RecyclerView.setMenuItems(items : ArrayList<Food>?){
    if(adapter == null){
        this.adapter = MenuAdapter()
        this.layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as MenuAdapter).setItems(it) }
}

