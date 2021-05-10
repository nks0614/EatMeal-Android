package com.project.eatmeal.ui.meal


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.databinding.FragmentMealBinding
import com.project.simplecode.spDateFormat
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.util.*

class MealFragment : BindingFragment<FragmentMealBinding>() {

    private val viewModel : MealViewModel by lazy {
        getViewModel(MealViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_meal

    override fun observeEvent() {
        with(viewModel){
            previousClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                date.value = spDateFormat("YYYY년 MM월 dd일", --progress)
                getMeal()
            })

            nextClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                date.value = spDateFormat("YYYY년 MM월 dd일", ++progress)
                getMeal()
            })

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMeal()

        binding.scrollView.setOnRefreshListener {
            viewModel.getMeal()
        }
    }
}