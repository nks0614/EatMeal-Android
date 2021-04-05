package com.project.eatmeal.view.activity

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseActivity
import com.project.eatmeal.data.enums.SortNumber
import com.project.eatmeal.databinding.ActivityMainBinding
import com.project.eatmeal.view.fragment.MealFragment
import com.project.eatmeal.view.fragment.MemberFragment
import com.project.eatmeal.view.fragment.MenuFragment
import com.project.eatmeal.viewmodel.MainViewModel
import com.project.eatmeal.viewmodel.MealViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel = MainViewModel()
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun init() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, MealFragment())
            .commit()

        binding.navigationView.setOnNavigationItemSelectedListener(ItemSelectedListener())
    }

    override fun observerViewModel() {

    }

    inner class ItemSelectedListener : BottomNavigationView.OnNavigationItemSelectedListener{
        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            val fm = supportFragmentManager.beginTransaction()

            when(item.itemId){
                R.id.meal -> fm.replace(R.id.frameLayout, MealFragment()).commit()
                R.id.menu -> fm.replace(R.id.frameLayout, MenuFragment()).commit()
                R.id.member -> fm.replace(R.id.frameLayout, MemberFragment()).commit()
            }
            return true
        }
    }
}

