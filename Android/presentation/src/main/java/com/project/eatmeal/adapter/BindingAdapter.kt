package com.project.eatmeal.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.base.BindingItem


@BindingAdapter("todayMealItems")
fun RecyclerView.setTodayMealItems(items: ArrayList<BindingItem>?) {
    if (adapter == null) {
        this.adapter = TodayMealAdapter()
        this.layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as TodayMealAdapter).setItems(it) }
}

@BindingAdapter("menuItems")
fun RecyclerView.setMenuItems(items : ArrayList<BindingItem>?){
    if(adapter == null){
        this.adapter = MenuAdapter()
        this.layoutManager = LinearLayoutManager(context)
    }
    items?.let { (adapter as MenuAdapter).setItems(it) }
}

