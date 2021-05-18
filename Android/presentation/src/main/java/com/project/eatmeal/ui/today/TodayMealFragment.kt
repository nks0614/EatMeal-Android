package com.project.eatmeal.ui.today

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.project.domain.model.response.Food
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.base.EventObserver
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.FragmentTodayMealBinding
import com.project.simplecode.spfToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*
import kotlin.collections.ArrayList

class TodayMealFragment : BindingFragment<FragmentTodayMealBinding>() {

    private val viewModel : TodayMealViewModel by lazy {
        getViewModel(TodayMealViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_today_meal

    override fun observeEvent() {
        with(viewModel){
            onErrorEvent.observe(this@TodayMealFragment, EventObserver {
                spfToastShort(it)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.isLoading.value = false
        with(CashingData) {
            if(todayMealData.size != 0) {
                viewModel.breakfastList.value = todayMealData[TODAY_MEAL_BREAKFAST_LIST] as ArrayList<BindingItem>
                viewModel.lunchList.value = todayMealData[TODAY_MEAL_LUNCH_LIST] as ArrayList<BindingItem>
                viewModel.dinnerList.value = todayMealData[TODAY_MEAL_DINNER_LIST] as ArrayList<BindingItem>
                viewModel.isLoading.value = true
            } else {
                viewModel.getTodayMeal()
            }
        }



    }


}