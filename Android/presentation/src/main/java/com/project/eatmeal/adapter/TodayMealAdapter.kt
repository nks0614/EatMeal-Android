package com.project.eatmeal.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.domain.model.response.Food
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.databinding.ItemTodayMealBinding

class TodayMealAdapter : RecyclerView.Adapter<TodayMealAdapter.ViewHolder>() {

    private var mealList = mutableListOf<BindingItem>()

    inner class ViewHolder(val binding: ItemTodayMealBinding) : RecyclerView.ViewHolder(binding.root) { }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemTodayMealBinding =
            ItemTodayMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).bind(holder.binding)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = mealList.size

    private fun getItem(position: Int) : BindingItem {
        return mealList[position]
    }

    fun setItem(mealList : ArrayList<BindingItem>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }
}