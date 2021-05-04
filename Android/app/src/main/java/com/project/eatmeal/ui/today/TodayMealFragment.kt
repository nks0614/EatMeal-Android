package com.project.eatmeal.ui.today

import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.FragmentTodayMealBinding
import java.util.*
import kotlin.collections.ArrayList

class TodayMealFragment : BaseFragment<FragmentTodayMealBinding, TodayMealViewModel>() {
    override val viewModel: TodayMealViewModel
        get() = ViewModelProvider(this)[TodayMealViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_today_meal

    val now : Calendar = Calendar.getInstance()

    override fun init() {
        if(CashingData.todayMealData[CashingData.TODAY_MEAL_BREAKFAST_LIST] == null){
            viewModel.getTodayMeal()
        } else {
            with(CashingData){
                viewModel.breakfastList.value = this.todayMealData[TODAY_MEAL_BREAKFAST_LIST] as ArrayList<Food>?
                viewModel.lunchList.value = this.todayMealData[TODAY_MEAL_LUNCH_LIST] as ArrayList<Food>?
                viewModel.dinnerList.value = this.todayMealData[TODAY_MEAL_DINNER_LIST] as ArrayList<Food>?
            }
        }

    }

    override fun observerViewModel() {
        with(viewModel){
            breakfastClick.observe(this@TodayMealFragment , androidx.lifecycle.Observer {
                viewRotateAnimation(breakfastVisible.value!!, 1)
            })

            lunchClick.observe(this@TodayMealFragment, androidx.lifecycle.Observer {
                viewRotateAnimation(lunchVisible.value!!, 2)
            })

            dinnerClick.observe(this@TodayMealFragment, androidx.lifecycle.Observer {
                viewRotateAnimation(dinnerVisible.value!!, 3)
            })
        }

    }

    private fun viewRotateAnimation(check : Boolean, type : Int){
        var animation = R.anim.rotate
        if(check){
            animation = R.anim.reverse_rotate
        }

        when(type){
            1 -> {
                binding.breakfastArrow.startAnimation(AnimationUtils.loadAnimation(context, animation))
                with(viewModel){
                    breakfastVisible.value = !(breakfastVisible.value!!)
                }
            }
            2 -> {
                binding.lunchArrow.startAnimation(AnimationUtils.loadAnimation(context, animation))
                with(viewModel){
                    lunchVisible.value = !(lunchVisible.value!!)
                }
            }
            3 -> {
                binding.dinnerArrow.startAnimation(AnimationUtils.loadAnimation(context, animation))
                with(viewModel){
                    dinnerVisible.value = !(dinnerVisible.value!!)
                }
            }
        }
    }

}