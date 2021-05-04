package com.project.eatmeal.ui.meal


import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentMealBinding
import com.project.simplecode.spDateFormat
import java.util.*

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_meal

    override val viewModel: MealViewModel
        get() = ViewModelProvider(this)[MealViewModel::class.java]

    override fun init() {
        initialize()

        binding.scrollView.setOnRefreshListener {
            initialize()
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
            })
        }
    }

    private fun initialize(){
        with(viewModel){
            getMeal()
        }
    }


}