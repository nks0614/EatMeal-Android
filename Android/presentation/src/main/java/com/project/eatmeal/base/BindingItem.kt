package com.project.eatmeal.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.project.eatmeal.BR

data class BindingItem(
    val viewModel : Any,
    val navigator : Any,
    @LayoutRes val layoutId : Int
) {
    fun bind(binding : ViewDataBinding) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.navigator, navigator)
    }
}
