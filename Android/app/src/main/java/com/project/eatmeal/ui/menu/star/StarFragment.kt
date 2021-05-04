package com.project.eatmeal.ui.menu.star

import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentStarBinding

class StarFragment : BaseFragment<FragmentStarBinding, StarViewModel>() {
    override val viewModel: StarViewModel
        get() = ViewModelProvider(this)[StarViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_star

    override fun init() {

    }

    override fun observerViewModel() {

    }

}