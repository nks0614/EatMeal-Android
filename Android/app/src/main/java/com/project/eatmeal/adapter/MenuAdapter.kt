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
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.body.StarBody
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.data.response.MResponseNoData
import com.project.eatmeal.data.response.MemberStarFood
import com.project.eatmeal.databinding.ItemLoadingBinding
import com.project.eatmeal.databinding.ItemMenuBinding
import com.project.eatmeal.network.NetworkClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            Log.d("MYTAG", starRating.rating.toString())
            if(starRating.rating != 0f){
                giveStar((starRating.rating * 2).toInt(), food.name, binding.root.context)
            } else {
                Toast.makeText(binding.root.context, "별점을 입력하지 않았습니다!", Toast.LENGTH_SHORT).show()
            }
        })

        val alertDialog = builder.create()
        alertDialog.show()

    }

    private fun giveStar(star : Int, name : String, context : Context) {
        val id = CashingData.memberData[CashingData.MEMBER_ID] as String?

        if(id == null) {
            Toast.makeText(context, "로그인해주시기 바랍니다!", Toast.LENGTH_SHORT).show()
            return
        }

        val list = CashingData.memberData[CashingData.MEMBER_FOODS] as ArrayList<MemberStarFood>

        for(starFood in list) {
            if(starFood.name == name) {
                Toast.makeText(context, "이미 별점을 준 메뉴입니다!", Toast.LENGTH_SHORT).show()
                return
            }
        }

        NetworkClient.API.star(
            StarBody(
                id = id,
                star = star,
                name = name
            )
        ).enqueue(object : Callback<MResponseNoData> {
            override fun onResponse(call: Call<MResponseNoData>, response: Response<MResponseNoData>) {
                if(response.code() == 200) Toast.makeText(context, "${star}점을 주었습니다!", Toast.LENGTH_SHORT).show()
                else Toast.makeText(context, "별점 주는데 실패했습니다. 사유 : ${response.code()}", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<MResponseNoData>, t: Throwable) {
                Toast.makeText(context, "별점 주는데 실패했습니다. 사유 : ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

    }


}