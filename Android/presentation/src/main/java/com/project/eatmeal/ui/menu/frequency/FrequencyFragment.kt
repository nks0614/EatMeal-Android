package com.project.eatmeal.ui.menu.frequency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.base.EventObserver
import com.project.eatmeal.databinding.FragmentFrequencyBinding
import com.project.simplecode.spfToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel

class FrequencyFragment : BindingFragment<FragmentFrequencyBinding>() {

    private val viewModel : FrequencyViewModel by lazy {
        getViewModel(FrequencyViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_frequency

    override fun observeEvent() {
        with(viewModel) {
            onErrorEvent.observe(this@FrequencyFragment, EventObserver{
                spfToastShort(it)
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMenuFrequency()
    }
}