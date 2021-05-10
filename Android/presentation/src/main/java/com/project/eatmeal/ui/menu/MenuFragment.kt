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

//    override fun init() {


//        bindSpinnerAdapter()
//        listenerSetting()
//        viewModel.getMenuList(0)

//        binding.refreshView.setOnRefreshListener {
//            viewModel.getMenuList(0)
//        }
//    }

//    override fun observerViewModel() {
//        with(viewModel) {
//            searchText.observe(this@MenuFragment, Observer {
//                if (searchText.value?.length!! > 0) {
//                    binding.cancelText.visibility = View.VISIBLE
//                } else {
//                    binding.cancelText.visibility = View.INVISIBLE
//                }
//            })
//
//            cancelClick.observe(this@MenuFragment, Observer {
//                searchText.value = ""
//            })
//
//            isGetMenuList.observe(this@MenuFragment, Observer {
//                binding.refreshView.isRefreshing = false
//            })
//        }
//    }

//    private fun bindSpinnerAdapter() {
//        with(CashingData) {
//            if (mealData[MENU_KIND_SPINNER] as SpinnerAdapter? == null) {
//                ArrayAdapter.createFromResource(
//                    requireContext(),
//                    R.array.kind_array,
//                    android.R.layout.simple_spinner_item
//                ).also { adapter ->
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    mealData[MENU_KIND_SPINNER] = adapter
//                    binding.kindSpinner.adapter = adapter
//                }
//            } else {
//                binding.kindSpinner.adapter = mealData[MENU_KIND_SPINNER] as SpinnerAdapter?
//            }
//
//            if (mealData[MENU_SORT_SPINNER] as SpinnerAdapter? == null) {
//                ArrayAdapter.createFromResource(
//                    requireContext(),
//                    R.array.sort_array,
//                    android.R.layout.simple_spinner_item
//                ).also { adapter ->
//                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//                    mealData[MENU_SORT_SPINNER] = adapter
//                    binding.sortSpinner.adapter = adapter
//                }
//            } else {
//                binding.sortSpinner.adapter = mealData[MENU_SORT_SPINNER] as SpinnerAdapter?
//            }
//        }
//
//    }
//
//    private fun listenerSetting() {
//        binding.kindSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                viewModel.kindNum = position
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//
//        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                viewModel.sortNum = if (position == 0) SortNumber.DESC
//                else SortNumber.ASC
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//        }
//
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
