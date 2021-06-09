package com.project.eatmeal.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.data.CashingData
import com.project.eatmeal.databinding.ActivityMainBinding
import com.project.eatmeal.widget.EatMealApplication
import com.project.eatmeal.widget.setupWithNavController
import com.project.simplecode.spaToastShort
import java.lang.Exception
import java.lang.StringBuilder
import java.net.NetworkInterface
import java.util.*

class MainActivity : BindingActivity<ActivityMainBinding>() {

    private val backPressHandler : OnBackPressHandler = OnBackPressHandler()
    private var currentNavController: LiveData<NavController>? = null

    override fun getLayoutRes(): Int = R.layout.activity_main

    override fun observeEvent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            setUpBottomNavigationBar()
        }

        CashingData.MAC_ADDRESS = getMacAddress()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setUpBottomNavigationBar()
    }

    private fun setUpBottomNavigationBar() {
        val navGraphIds = listOf(
            R.navigation.navigation_today_meal,
            R.navigation.navigation_meal,
            R.navigation.navigation_menu,
            R.navigation.navigation_info
        )

        val controller = binding.navigationView.setupWithNavController(
            navGraphIds, supportFragmentManager, R.id.nav_host_container, intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    private fun getMacAddress() : String {
        try {
            val list = Collections.list(NetworkInterface.getNetworkInterfaces())
            for(n in list) {
                if(!n.name.equals("wlan0")) continue
                val macBytes : ByteArray = n.hardwareAddress ?: return ""
                val res = StringBuilder()

                for(b in macBytes) {
                    res.append(String.format("%02X", b))
                }

                if(res.isNotEmpty()) {
                    res.deleteCharAt(res.length - 1)
                }
                return res.toString()
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return ""
    }

    override fun onBackPressed() {
        backPressHandler.onBackPressed()
    }

    inner class OnBackPressHandler() {
        private var backPressHandler : Long = 0

        fun onBackPressed() {
            if(System.currentTimeMillis() > backPressHandler + 2000) {
                backPressHandler = System.currentTimeMillis()
                spaToastShort("한번 더 누르시면 종료됩니다.")
            } else {
                finish()
            }
        }
    }

}

