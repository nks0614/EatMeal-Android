package com.project.eatmeal.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.ItemLoadingBinding
import com.project.eatmeal.databinding.ItemMenuBinding

class MenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private var menuList = ArrayList<Food>()

    inner class ItemViewHolder(val binding: ItemMenuBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(food: Food){
            binding.setVariable(BR.food, food)
            binding.columnView.setOnClickListener {
                makeDialog(binding, food)
            }
        }
    }

    inner class LoadingViewHolder(val binding: ItemLoadingBinding) : RecyclerView.ViewHolder(binding.root){

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
                val binding = ItemMenuBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                ItemViewHolder(binding)
            }
            else -> {
                val binding = ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
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

    fun setItems(menuList: ArrayList<Food>){
        Log.d("menuList", "${menuList}")
        this.menuList = menuList
        notifyDataSetChanged()
    }

    private fun makeDialog(binding : ItemMenuBinding, food : Food) {
        val builder = AlertDialog.Builder(binding.root.context)
        val inflater : LayoutInflater = binding.root.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view : View = inflater.inflate(R.layout.dialog_menu_detail, null)
        val breakfast = view.findViewById<TextView>(R.id.breakfastFreText)
        val lunch = view.findViewById<TextView>(R.id.lunchFreText)
        val dinner = view.findViewById<TextView>(R.id.dinnerFreText)
        val star = view.findViewById<TextView>(R.id.starText)
        val starRating = view.findViewById<RatingBar>(R.id.starRatingBar)

        breakfast.text = food.breakfast.toString()
        lunch.text = food.lunch.toString()
        dinner.text = food.dinner.toString()
        star.text = food.star.toString()

        builder.setView(view)
        builder.setPositiveButton("별점 주기", DialogInterface.OnClickListener { dialog, which ->
            Log.d("tests", starRating.rating.toString())
            if(starRating.rating != 0f){
                Toast.makeText(binding.root.context, "${starRating.rating * 2}점", Toast.LENGTH_LONG).show()
            }
        })

        val alertDialog = builder.create()
        alertDialog.show()

    }

}