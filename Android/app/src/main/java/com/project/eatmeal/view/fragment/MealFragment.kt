package com.project.eatmeal.view.fragment


import android.view.animation.AnimationUtils
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentMealBinding
import com.project.eatmeal.viewmodel.MealViewModel
import java.util.*

class MealFragment : BaseFragment<FragmentMealBinding, MealViewModel>() {

    override val layoutRes: Int
        get() = R.layout.fragment_meal

    override val viewModel: MealViewModel
        get() = ViewModelProvider(this)[MealViewModel::class.java]


    override fun init() {
        with(viewModel){
            getTodayMeal()
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