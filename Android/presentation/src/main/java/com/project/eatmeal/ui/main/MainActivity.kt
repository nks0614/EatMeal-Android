package com.project.eatmeal.ui.main

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.databinding.ActivityMainBinding
import com.project.eatmeal.widget.EatMealApplication
import com.project.eatmeal.widget.setupWithNavController

class MainActivity : BindingActivity<ActivityMainBinding>() {

    private var currentNavController: LiveData<NavController>? = null

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun observeEvent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            setUpBottomNavigationBar()
        }

        val sharedPref = getSharedPreferences(EatMealApplication.SHARED, Context.MODE_PRIVATE)
        val id = sharedPref.getString(EatMealApplication.ID, null) ?: return
        val pw = sharedPref.getString(EatMealApplication.PW, null) ?: return
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationBar()
    }

    private fun setUpBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_meal,
            R.navigation.navigation_info,
            R.navigation.navigation_menu,
            R.navigation.navigation_today_meal
        )

        val controller = binding.navigationView.setupWithNavController(
            navGraphIds, supportFragmentManager, R.id.nav_host_container, intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }


}

