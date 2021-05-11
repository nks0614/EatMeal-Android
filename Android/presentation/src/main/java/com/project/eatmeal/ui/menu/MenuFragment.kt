package com.project.eatmeal.ui.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.adapter.ViewPagerAdapter
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.databinding.FragmentMenuBinding
import com.project.eatmeal.ui.menu.frequency.FrequencyFragment
import com.project.eatmeal.ui.menu.star.StarFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MenuFragment : BindingFragment<FragmentMenuBinding>() {

    private val viewModel : MenuViewModel by lazy {
        getViewModel(MenuViewModel::class)
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_menu

    override fun observeEvent() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPagerAdapter = ViewPagerAdapter(requireActivity())
        viewPagerAdapter.setFragmentList(arrayListOf(FrequencyFragment(), StarFragment()))

        binding.viewPager.adapter = viewPagerAdapter
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
}

//    private fun listenerSetting() {
//        binding.searchEdit.setOnEditorActionListener { v, actionId, event ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                if(!viewModel.searchText.value.isNullOrBlank()){
//                    viewModel.page = 0
//                    viewModel.getSearchList(0)
//                }
//                val im = (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//                im.hideSoftInputFromWindow(binding.searchEdit.windowToken, 0)
//            }
//            true
//        }
//
//        binding.menuRcView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                val lastVisibleItemPosition =
//                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
//                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
//
//                // 스크롤이 끝에 도달했는지 확인
//                if (!binding.menuRcView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
//                    viewModel.page++
//                    if (viewModel.listType) {
//                        viewModel.getMenuList(1)
//                    } else {
//                        viewModel.getSearchList(1)
//                    }
//                }
//            }
//        })
//
//    }
