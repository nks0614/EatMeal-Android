package com.project.eatmeal.ui.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.adapter.ViewPagerAdapter
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.databinding.FragmentMenuBinding
import com.project.eatmeal.ui.main.MainActivity
import com.project.eatmeal.ui.menu.frequency.FrequencyFragment
import com.project.eatmeal.ui.menu.search.SearchActivity
import com.project.eatmeal.ui.menu.star.StarFragment
import com.project.simplecode.spfIntent
import com.project.simplecode.spfToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MenuFragment : BindingFragment<FragmentMenuBinding>() {

    private val viewModel: MenuViewModel by lazy {
        getViewModel(MenuViewModel::class)
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_menu

    override fun observeEvent() {
        with(viewModel) {
            searchBtn.observe(this@MenuFragment, Observer {
                goSearch()
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
        viewPagerAdapter = ViewPagerAdapter(requireActivity())
        viewPagerAdapter.setFragmentList(arrayListOf(FrequencyFragment(), StarFragment()))

        binding.viewPager.adapter = viewPagerAdapter

        binding.searchBox.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                goSearch()
            }
            true
        }

    }

    override fun onResume() {
        super.onResume()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> tab.text = "전체 빈도"
                1 -> tab.text = "별점"
            }
            binding.viewPager.setCurrentItem(tab.position, true)
        }.attach()
    }

    private fun goSearch() {
        if (!viewModel.searchText.value.isNullOrBlank()) {
            val i = Intent(context, SearchActivity::class.java)
            i.putExtra("name", viewModel.searchText.value)
            startActivity(i)
        } else {
            spfToastShort("이름을 입력해주시기 바랍니다.")
        }
        val im =
            (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(binding.searchBox.windowToken, 0)
    }
}
