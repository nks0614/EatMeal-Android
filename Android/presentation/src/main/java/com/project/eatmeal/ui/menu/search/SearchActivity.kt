package com.project.eatmeal.ui.menu.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.databinding.ActivitySearchBinding
import com.project.eatmeal.widget.MenuCustomDialog
import org.koin.androidx.viewmodel.ext.android.getViewModel

class SearchActivity : BindingActivity<ActivitySearchBinding>() {

    private val viewModel : SearchViewModel by lazy {
        getViewModel(SearchViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.activity_search

    override fun observeEvent() {
        with(viewModel) {
            itemFood.observe(this@SearchActivity, Observer {
                val dialog = MenuCustomDialog(this@SearchActivity, it, giveStarUseCase)
                dialog.event.observe(this@SearchActivity, Observer {
                    isLoading.value = true
                    getSearchMenu()
                })
                dialog.show()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
        listenerSetting()

        viewModel.nameText.value = intent.getStringExtra("name")
        viewModel.getSearchMenu()
    }

    private fun listenerSetting() {
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
                        viewModel.addSearchMenu()
                    }
                }
            }
        })
    }
}