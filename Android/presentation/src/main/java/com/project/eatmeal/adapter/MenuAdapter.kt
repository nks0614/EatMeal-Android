package com.project.eatmeal.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.domain.model.response.Food
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.databinding.ItemMenuBinding


class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private var menuList = ArrayList<BindingItem>()

    inner class ItemViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root) { }

    inner class LoadingViewHolder(view : View) : RecyclerView.ViewHolder(view) { }

    override fun getItemViewType(position: Int): Int {
        return when(menuList[position].layoutId){
            R.layout.item_loading -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            VIEW_TYPE_ITEM -> {
                val binding = ItemMenuBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemViewHolder(binding)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            getItem(position).bind(holder.binding)
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int = menuList.size

    private fun getItem(position: Int) : BindingItem {
        return menuList[position]
    }

    fun setItems(menuList: ArrayList<BindingItem>){
        Log.d("menuList", "${menuList}")
        this.menuList = menuList
        notifyDataSetChanged()
    }



}