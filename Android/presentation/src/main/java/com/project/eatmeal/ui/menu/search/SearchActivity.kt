package com.project.eatmeal.ui.menu.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity : BindingActivity<ActivitySearchBinding>() {

    private val viewModel : SearchViewModel by lazy {
        getViewModel(SearchViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun observeEvent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }
}