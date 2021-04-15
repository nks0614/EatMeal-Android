package com.project.eatmeal.view.fragment

import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.SpinnerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.R
import com.project.eatmeal.adapter.MenuAdapter
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.data.enums.SortNumber
import com.project.eatmeal.data.response.Food
import com.project.eatmeal.databinding.FragmentMenuBinding
import com.project.eatmeal.viewmodel.MenuViewModel

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel
        get() = ViewModelProvider(this)[MenuViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.fragment_menu

    override fun init() {
        bindSpinnerAdapter()
        listenerSetting()

        if (CashingData.menuData[CashingData.MENU_LIST] == null) {
            viewModel.getMenuList()
        }
    }

    override fun observerViewModel() {
        with(viewModel) {
            searchText.observe(this@MenuFragment, Observer {
                Log.d("tests", searchText.value?.length!!.toString())
                if (searchText.value?.length!! > 0) {
                    binding.cancelText.visibility = View.VISIBLE
                } else {
                    binding.cancelText.visibility = View.INVISIBLE
                }
            })

            filterClick.observe(this@MenuFragment, Observer {
                Log.d("tests", "filter")
                when (binding.filterView.visibility) {
                    View.GONE -> binding.filterView.visibility = View.VISIBLE
                    View.VISIBLE -> binding.filterView.visibility = View.INVISIBLE
                }
            })

            cancelClick.observe(this@MenuFragment, Observer {
                searchText.value = ""
            })

            isGetMenuList.observe(this@MenuFragment, Observer {
                bindView()
            })
        }
    }

    private fun bindSpinnerAdapter() {
        with(CashingData) {
            if (mealData[MENU_KIND_SPINNER] as SpinnerAdapter? == null) {
                Log.d("tests", "new Spinner1")
                ArrayAdapter.createFromResource(
                        requireContext(),
                        R.array.kind_array,
                        android.R.layout.simple_spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    mealData[MENU_KIND_SPINNER] = adapter
                    binding.kindSpinner.adapter = adapter
                }
            } else {
                binding.kindSpinner.adapter = mealData[MENU_KIND_SPINNER] as SpinnerAdapter?
            }

            if (mealData[MENU_SORT_SPINNER] as SpinnerAdapter? == null) {
                Log.d("tests", "new Spinner2")
                ArrayAdapter.createFromResource(
                        requireContext(),
                        R.array.sort_array,
                        android.R.layout.simple_spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    mealData[MENU_SORT_SPINNER] = adapter
                    binding.sortSpinner.adapter = adapter
                }
            } else {
                binding.sortSpinner.adapter = mealData[MENU_SORT_SPINNER] as SpinnerAdapter?
            }
        }

    }

    private fun listenerSetting() {
        binding.kindSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.kindNum = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.sortSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.sortNum = if (position == 0) SortNumber.DESC
                else SortNumber.ASC
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.searchEdit.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Log.d("tests", "kind : ${viewModel.kindNum}, sort : ${viewModel.sortNum}, name : ${viewModel.searchText.value}")
            }
            true
        }

        binding.menuRcView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                        (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                // 스크롤이 끝에 도달했는지 확인
                if (!binding.menuRcView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    viewModel.page++
                    viewModel.getMenuList()
                }
            }
        })

    }

    private fun bindView() {
        with(CashingData) {
            Log.d("tests", "${menuData[MENU_LIST] as ArrayList<Food>?}")
            viewModel.menuList.value = menuData[MENU_LIST] as ArrayList<Food>?
        }
    }
}