package com.project.eatmeal.ui.main

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.project.eatmeal.R
import com.project.eatmeal.base.BaseActivity
import com.project.eatmeal.databinding.ActivityMainBinding
import com.project.eatmeal.widget.setupWithNavController

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private var currentNavController: LiveData<NavController>? = null

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this)[MainViewModel::class.java]
    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun init() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            setUpBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationBar()
    }

    override fun observerViewModel() {}

    private fun setUpBottomNavigationBar() {
        val navGrapshIds = listOf(
            R.navigation.navigation_meal,
            R.navigation.navigation_member,
            R.navigation.navigation_menu,
            R.navigation.navigation_today_meal
        )

        val controller = binding.navigationView.setupWithNavController(
            navGrapshIds, supportFragmentManager, R.id.nav_host_container, intent
        )

//        controller.observe(this, Observer { setupActionBarWithNavController(it) })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}

