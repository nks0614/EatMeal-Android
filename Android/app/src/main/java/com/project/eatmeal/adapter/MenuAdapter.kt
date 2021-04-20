package com.project.eatmeal.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.BR
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.ItemLoadingBinding
import com.project.eatmeal.databinding.ItemMenuBinding
import com.project.eatmeal.databinding.ItemTodayMealBinding

class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private var menuList = ArrayList<Food>()

    inner class ItemViewHolder(val binding : ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food : Food){
            binding.setVariable(BR.food, food)
        }
    }

    inner class LoadingViewHolder(val binding : ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun getItemViewType(position: Int): Int {
        return when(menuList[position].name){
            "" -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            VIEW_TYPE_ITEM -> {
                val binding = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> {
                val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.bind(menuList[position])
        }
    }

    override fun getItemCount(): Int = menuList.size

    fun setItems(menuList : ArrayList<Food>){
        Log.d("menuList" , "${menuList}")
        this.menuList = menuList
        notifyDataSetChanged()
    }

}