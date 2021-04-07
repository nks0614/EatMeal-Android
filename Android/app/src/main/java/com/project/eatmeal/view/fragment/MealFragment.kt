package com.project.eatmeal.view.fragment


import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentMealBinding
import com.project.eatmeal.viewmodel.MealViewModel
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
            breakfastClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                checkViewRotate(breakfastVisible.value!!, 1)
            })

            lunchClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                checkViewRotate(lunchVisible.value!!, 2)
            })

            dinnerClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                checkViewRotate(dinnerVisible.value!!, 3)
            })

            previousClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                progress -= 1
                date.value = spDateFormat("YYYY년 MM월 dd일", progress)
                getMeal()
            })

            nextClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                progress += 1
                date.value = spDateFormat("YYYY년 MM월 dd일", progress)
                getMeal()
            })

            isGetMeal.observe(this@MealFragment, androidx.lifecycle.Observer {
                Log.d("tests", "isGetMeal")
                if(isGetMeal.value == isGetTodayMeal.value){
                    binding.scrollView.isRefreshing = false
                }
            })

            isGetTodayMeal.observe(this@MealFragment, androidx.lifecycle.Observer {
                Log.d("tests", "isGetTodayMeal")
                if(isGetMeal.value == isGetTodayMeal.value){
                    binding.scrollView.isRefreshing = false
                }
            })

        }
    }

    fun initialize(){
        with(viewModel){
            getMeal()
            getTodayMeal()
        }
    }
    fun checkViewRotate(check : Boolean, type : Int){
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