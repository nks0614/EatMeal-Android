package com.project.eatmeal.view.fragment

import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentTodayMealBinding
import com.project.eatmeal.viewmodel.TodayMealViewModel

class TodayMealFragment : BaseFragment<FragmentTodayMealBinding,TodayMealViewModel>() {
    override val viewModel: TodayMealViewModel
        get() = ViewModelProvider(this)[TodayMealViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_today_meal

    override fun init() {

    }

    override fun observerViewModel() {

    }

}