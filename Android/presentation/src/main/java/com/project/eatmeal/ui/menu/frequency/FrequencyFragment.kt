package com.project.eatmeal.ui.menu.frequency

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.base.BindingItem
import com.project.eatmeal.widget.EventObserver
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.FragmentFrequencyBinding
import com.project.eatmeal.widget.MenuCustomDialog
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

            itemFood.observe(this@FrequencyFragment, Observer {
                val dialog = MenuCustomDialog(context, it, giveStarUseCase)
                dialog.event.observe(this@FrequencyFragment, Observer {
                    getMenuFrequency()
                    page = 0
                    binding.menuRcView.smoothScrollToPosition(0)
                    isFind.value = false
                })
                dialog.show()
            })

            menuList.observe(this@FrequencyFragment, Observer {
                binding.swipeLayout.isRefreshing = false
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
        listenerSetting()

        val data : Any? = CashingData.menuData[CashingData.MENU_FREQUENCY_LIST]

        if(data != null) {
            viewModel.menuList.value = data as ArrayList<BindingItem>
        } else {
            viewModel.getMenuFrequency()
        }
    }

    override fun onPause() {
        super.onPause()
        with(viewModel) {
            viewModel.page = 0
            disposables.clear()
        }
        Log.d("MYTAG", "FrequencyFragment Paused")
    }

    private fun listenerSetting() {
        binding.swipeLayout.setOnRefreshListener {
            viewModel.page = 0
            viewModel.getMenuFrequency()
            viewModel.isFind.value = false
        }

        binding.menuRcView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1

                // 스크롤이 끝에 도달했는지 확인
                if (!binding.menuRcView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
                    if(!viewModel.isFind.value!!) {
                        viewModel.page++
                        viewModel.addMenuFrequency()
                    }

                }
            }
        })
    }
}