package com.project.eatmeal.ui.main

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
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

        if(!networkCheck()) {
            spaToastShort("인터넷 연결이 원활하지 않습니다. 다시 시도해주시기 바랍니다.")
            finish()
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
        val a = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
        Log.d("MYTAG", a)
        return a

    }

    private fun networkCheck() : Boolean {
        val manager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = manager.activeNetwork ?: return false
            val actNw = manager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val nwInfo = manager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
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

