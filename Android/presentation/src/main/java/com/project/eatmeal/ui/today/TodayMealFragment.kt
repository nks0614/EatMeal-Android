package com.project.eatmeal.ui.today

import android.os.Bundle
import android.util.Log
import android.view.View
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.widget.EventObserver
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.FragmentTodayMealBinding
import com.project.simplecode.spDateFormat
import com.project.simplecode.spfToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.collections.ArrayList

class TodayMealFragment : BindingFragment<FragmentTodayMealBinding>() {

    val time = spDateFormat("HH", 0).toInt()

    private val viewModel : TodayMealViewModel by lazy {
        getViewModel(TodayMealViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_today_meal

    override fun observeEvent() {
        with(viewModel){
            breakfastClick.observe(this@TodayMealFragment, androidx.lifecycle.Observer {
                binding.breakfastText.setTextColor(resources.getColor(R.color.dark_blue))
                binding.lunchText.setTextColor(resources.getColor(R.color.gray))
                binding.dinnerText.setTextColor(resources.getColor(R.color.gray))
                checkList(0)


            })

            lunchClick.observe(this@TodayMealFragment, androidx.lifecycle.Observer {
                binding.breakfastText.setTextColor(resources.getColor(R.color.gray))
                binding.lunchText.setTextColor(resources.getColor(R.color.dark_blue))
                binding.dinnerText.setTextColor(resources.getColor(R.color.gray))
                checkList(1)

            })

            dinnerClick.observe(this@TodayMealFragment, androidx.lifecycle.Observer {
                binding.breakfastText.setTextColor(resources.getColor(R.color.gray))
                binding.lunchText.setTextColor(resources.getColor(R.color.gray))
                binding.dinnerText.setTextColor(resources.getColor(R.color.dark_blue))
                checkList(2)

            })

            getFinish.observe(this@TodayMealFragment, androidx.lifecycle.Observer {
                if(time < 8) {
                    binding.breakfastText.setTextColor(resources.getColor(R.color.dark_blue))
                    list.value = breakfastList.value
                    checkList(0)
                } else if(time in 9..13) {
                    binding.lunchText.setTextColor(resources.getColor(R.color.dark_blue))
                    list.value = lunchList.value
                    checkList(1)
                } else {
                    binding.dinnerText.setTextColor(resources.getColor(R.color.dark_blue))
                    list.value = dinnerList.value
                    checkList(2)
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()

        with(CashingData) {
            if(todayMealData.size != 0) {
                viewModel.breakfastList.value = todayMealData[TODAY_MEAL_BREAKFAST_LIST] as ArrayList<BindingItem>
                viewModel.lunchList.value = todayMealData[TODAY_MEAL_LUNCH_LIST] as ArrayList<BindingItem>
                viewModel.dinnerList.value = todayMealData[TODAY_MEAL_DINNER_LIST] as ArrayList<BindingItem>
                viewModel.getFinish.call()
            } else {
                viewModel.getTodayMeal()
            }
        }
    }

    private fun checkList(type : Int) {
        with(viewModel) {
            when(type) {
                0 ->  {
                    if(breakfastList.value?.size == 0) {
                        hasMeal.value = true
                    } else {
                        hasMeal.value = false
                        list.value = breakfastList.value
                    }
                }
                1 -> {
                    if(lunchList.value?.size == 0) {
                        hasMeal.value = true
                    } else {
                        hasMeal.value = false
                        list.value = lunchList.value
                    }
                }
                2 -> {
                    if(dinnerList.value?.size == 0) {
                        hasMeal.value = true
                    } else {
                        hasMeal.value = false
                        list.value = dinnerList.value
                    }
                }
            }
        }

    }


}