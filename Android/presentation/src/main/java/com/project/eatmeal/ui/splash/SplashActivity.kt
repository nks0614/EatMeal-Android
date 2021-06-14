package com.project.eatmeal.ui.splash

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.eatmeal.ui.main.MainActivity
import com.project.simplecode.spaIntent
import com.project.simplecode.spaToastShort

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(!networkCheck()) {
            spaToastShort("인터넷 연결이 원활하지 않습니다. 다시 시도해주시기 바랍니다.")
            finish()
        }

        spaIntent(MainActivity::class.java)

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
}