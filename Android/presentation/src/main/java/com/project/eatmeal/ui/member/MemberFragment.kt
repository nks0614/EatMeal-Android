package com.project.eatmeal.ui.member

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.FragmentMemberBinding
import com.project.eatmeal.ui.login.LoginActivity
import com.project.eatmeal.ui.main.MainActivity
import com.project.eatmeal.widget.EatMealApplication
import com.project.simplecode.spfIntent
import com.project.simplecode.spfIntentNoFin
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MemberFragment : BindingFragment<FragmentMemberBinding>() {

    private val viewModel : MemberViewModel by lazy {
        getViewModel(MemberViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_member

    override fun observeEvent() {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
        if (CashingData.memberData.size != 0) {
            binding.loginBtn.visibility = View.GONE
            binding.logoutBtn.visibility = View.VISIBLE
            viewModel.memberName.value = CashingData.memberData[CashingData.MEMBER_NAME] as String
            viewModel.memberClass.value = CashingData.memberData[CashingData.MEMBER_CLASS] as String
        }
    }

}