package com.project.eatmeal.view.fragment


import android.util.Log
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.FragmentMealBinding
import com.project.eatmeal.viewmodel.MealViewModel
import com.project.simplecode.spDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_meal

    override val viewModel: MealViewModel
        get() = ViewModelProvider(this)[MealViewModel::class.java]

    override fun init() {
        if(CashingData.mealData.size == 0){
            Log.d("tests", "Don't has Cashing")
            initialize()
        }

        binding.scrollView.setOnRefreshListener {
            viewModel.getMeal()
        }
    }

    override fun observerViewModel() {
        with(viewModel){


            previousClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                date.value = spDateFormat("YYYY년 MM월 dd일", --progress)
                getMeal()
            })

            nextClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                date.value = spDateFormat("YYYY년 MM월 dd일", ++progress)
                getMeal()
            })

            isGetMeal.observe(this@MealFragment, androidx.lifecycle.Observer {
                binding.scrollView.isRefreshing = false
                bindView()
            })
        }
    }

    private fun initialize(){
        with(viewModel){
            getMeal()
        }
    }

    private fun bindView(){
        with(viewModel){
            with(CashingData){
                breakfast.value = mealData[MEAL_BREAKFAST].toString()
                lunch.value = mealData[MEAL_LUNCH].toString()
                dinner.value = mealData[MEAL_DINNER].toString()
            }
        }
    }


}