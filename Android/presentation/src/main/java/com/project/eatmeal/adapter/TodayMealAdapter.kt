package com.project.eatmeal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.databinding.ItemTodayMealBinding

class TodayMealAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var menuList = ArrayList<BindingItem>()

    inner class TodayMealViewHolder(val binding : ItemTodayMealBinding) : RecyclerView.ViewHolder(binding.root) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemTodayMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TodayMealViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        menuList[position].bind((holder as TodayMealViewHolder).binding)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = menuList.size

    fun setItems(menuList: ArrayList<BindingItem>) {
        this.menuList = menuList
        notifyDataSetChanged()
    }

}
