package com.project.eatmeal.view.activity

import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseActivity
import com.project.eatmeal.databinding.ActivityMainBinding
import com.project.eatmeal.view.fragment.MealFragment
import com.project.eatmeal.view.fragment.MemberFragment
import com.project.eatmeal.view.fragment.MenuFragment
import com.project.eatmeal.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.activity_main

    var mealFragment : Fragment? = null
    var menuFragment : Fragment? = null
    var memberFragment : Fragment? = null

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
                R.id.meal -> {
                    if (mealFragment == null) {
                        Log.d("tests", "new meal")
                        mealFragment = MealFragment()
                    }
                    fm.replace(R.id.frameLayout, mealFragment!!).commit()
                }
                R.id.menu -> {
                    if(menuFragment == null) {
                        Log.d("tests", "new menu")
                        menuFragment = MenuFragment()
                    }
                    fm.replace(R.id.frameLayout, menuFragment!!).commit()
                }
                R.id.member -> {
                    if(memberFragment == null) {
                        Log.d("tests", "new member")
                        memberFragment = MemberFragment()
                    }
                    fm.replace(R.id.frameLayout, memberFragment!!).commit()
                }
            }
            return true
        }
    }
}

