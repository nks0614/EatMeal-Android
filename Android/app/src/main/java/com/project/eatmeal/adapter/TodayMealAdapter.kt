package com.project.eatmeal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.ItemTodayMealBinding

class TodayMealAdapter : RecyclerView.Adapter<TodayMealAdapter.ViewHolder>() {

    private var mealList = mutableListOf<Food>()

    inner class ViewHolder(val binding: ItemTodayMealBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food : Food){
            binding.setVariable(BR.food, food)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemTodayMealBinding =
            ItemTodayMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mealList[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = mealList.size

    fun setItem(mealList : ArrayList<Food>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }
}