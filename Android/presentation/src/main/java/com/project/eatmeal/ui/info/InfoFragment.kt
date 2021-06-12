package com.project.eatmeal.ui.info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
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
            app3.observe(this@InfoFragment, Observer {
                val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://bit.ly/3n1eC2b"))
                startActivity(i)

            })
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