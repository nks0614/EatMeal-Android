package com.project.eatmeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.BR
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.ItemMenuBinding
import com.project.eatmeal.databinding.ItemTodayMealBinding

class MenuAdapter : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    private var menuList = ArrayList<Food>()

    inner class ViewHolder(val binding : ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food : Food){
            binding.setVariable(BR.food, food)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ItemMenuBinding =
                ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(menuList[position])
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int = menuList.size

    fun setItems(menuList : ArrayList<Food>){
        this.menuList = menuList
        notifyDataSetChanged()
    }


}