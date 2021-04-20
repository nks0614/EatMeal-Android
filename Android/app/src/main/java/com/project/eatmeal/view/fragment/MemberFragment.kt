package com.project.eatmeal.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.databinding.FragmentMemberBinding
import com.project.eatmeal.viewmodel.MealViewModel
import com.project.eatmeal.viewmodel.MemberViewModel

class MemberFragment : BaseFragment<FragmentMemberBinding, MemberViewModel>(){
    override val viewModel: MemberViewModel
        get() = ViewModelProvider(this)[MemberViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_member

    override fun init() {

    }

    override fun observerViewModel() {

    }
}