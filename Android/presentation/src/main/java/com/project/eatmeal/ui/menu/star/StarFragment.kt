package com.project.eatmeal.ui.menu.star

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.base.EventObserver
import com.project.eatmeal.databinding.FragmentStarBinding
import com.project.eatmeal.widget.MenuCustomDialog
import com.project.simplecode.spfToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel

class StarFragment : BindingFragment<FragmentStarBinding>() {

    private val viewModel : StarViewModel by lazy {
        getViewModel(StarViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_star

    override fun observeEvent() {
        with(viewModel) {
            onErrorEvent.observe(this@StarFragment, EventObserver{
                spfToastShort(it)
            })

            itemFood.observe(this@StarFragment, Observer {
                val dialog = MenuCustomDialog(context, it)
                dialog.show()
            })

            menuList.observe(this@StarFragment, Observer {
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
        viewModel.getMenuStar()

        listenerSetting()
    }

    override fun onPause() {
        super.onPause()
        with(viewModel) {
            disposables.clear()
            page = 0;
        }
        Log.d("MYTAG", "StarFragment Paused")
    }

    private fun listenerSetting() {
        binding.swipeLayout.setOnRefreshListener {
            viewModel.page = 0
            viewModel.getMenuStar()
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
                    viewModel.addMenuStar()
                }
            }
        })
    }
}