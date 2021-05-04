package com.project.eatmeal.view.fragment

import android.content.Context
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseFragment
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.FragmentMemberBinding
import com.project.eatmeal.view.activity.LoginActivity
import com.project.eatmeal.view.activity.MainActivity
import com.project.eatmeal.viewmodel.MemberViewModel
import com.project.eatmeal.widget.EatMealApplication
import com.project.simplecode.spfIntent
import com.project.simplecode.spfIntentNoFin

class MemberFragment : BaseFragment<FragmentMemberBinding, MemberViewModel>() {
    override val viewModel: MemberViewModel
        get() = ViewModelProvider(this)[MemberViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.fragment_member

    override fun init() {
        if (CashingData.memberData.size != 0) {
            binding.loginBtn.visibility = View.GONE
            binding.logoutBtn.visibility = View.VISIBLE
            viewModel.memberName.value = CashingData.memberData[CashingData.MEMBER_NAME] as String
            viewModel.memberClass.value = CashingData.memberData[CashingData.MEMBER_CLASS] as String
        }
    }

    override fun observerViewModel() {
        with(viewModel) {
            loginClick.observe(this@MemberFragment, Observer {
                spfIntentNoFin(LoginActivity::class.java)
            })

            logoutClick.observe(this@MemberFragment, Observer {
                CashingData.memberData.clear()
                val sharedPref = requireActivity().getSharedPreferences(EatMealApplication.SHARED, Context.MODE_PRIVATE)
                    .edit()
                sharedPref.clear()
                sharedPref.apply()
                spfIntent(MainActivity::class.java)
            })
        }
    }
}