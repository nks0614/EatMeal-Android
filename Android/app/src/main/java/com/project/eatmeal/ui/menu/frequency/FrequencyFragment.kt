package com.project.eatmeal.ui.menu.frequency

import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentFrequencyBinding

class FrequencyFragment : BaseFragment<FragmentFrequencyBinding, FrequencyViewModel>() {

    override val viewModel: FrequencyViewModel
        get() = ViewModelProvider(this)[FrequencyViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_frequency

    override fun init() {

    }

    override fun observerViewModel() {

    }
}