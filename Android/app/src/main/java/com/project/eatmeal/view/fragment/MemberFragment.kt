package com.project.eatmeal.view.fragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.FragmentMemberBinding
import com.project.eatmeal.view.activity.LoginActivity
import com.project.eatmeal.viewmodel.MemberViewModel
import com.project.simplecode.spfIntentNoFin

class MemberFragment : BaseFragment<FragmentMemberBinding, MemberViewModel>() {
    override val viewModel: MemberViewModel
        get() = ViewModelProvider(this)[MemberViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_member

    override fun init() {
        if (CashingData.memberData.size != 0) {
            binding.loginBtn.visibility = View.GONE
        }
    }

    override fun observerViewModel() {
        with(viewModel) {
            loginClick.observe(this@MemberFragment, Observer {
                spfIntentNoFin(LoginActivity::class.java)
            })
        }
    }
}