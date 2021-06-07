package com.project.eatmeal.ui.info

import android.os.Bundle
import android.view.View
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.databinding.FragmentInfoBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class InfoFragment : BindingFragment<FragmentInfoBinding>() {

    private val viewModel : InfoViewModel by lazy {
        getViewModel(InfoViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_info

    override fun observeEvent() {
        with(viewModel) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
    }

}