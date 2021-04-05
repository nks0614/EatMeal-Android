package com.project.eatmeal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentMenuBinding
import com.project.eatmeal.viewmodel.MealViewModel
import com.project.eatmeal.viewmodel.MenuViewModel

class MenuFragment : BaseFragment<FragmentMenuBinding, MenuViewModel>() {

    override val viewModel: MenuViewModel
        get() = ViewModelProvider(this)[MenuViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.fragment_menu

    override fun init() {

    }

    override fun observerViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}