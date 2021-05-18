package com.project.eatmeal.ui.menu.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.databinding.FragmentStarBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class StarFragment : BindingFragment<FragmentStarBinding>() {

    private val viewModel : StarViewModel by lazy {
        getViewModel(StarViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_star

    override fun observeEvent() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
    }
}