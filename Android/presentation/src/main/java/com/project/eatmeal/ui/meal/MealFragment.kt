package com.project.eatmeal.ui.meal


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.project.eatmeal.BR
import com.project.eatmeal.R
import com.project.eatmeal.base.BindingActivity
import com.project.eatmeal.base.BindingFragment
import com.project.eatmeal.base.EventObserver
import com.project.eatmeal.databinding.FragmentMealBinding
import com.project.simplecode.spDateFormat
import com.project.simplecode.spfToastShort
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.Exception
import java.util.*
import kotlin.math.abs

class MealFragment : BindingFragment<FragmentMealBinding>() {

    lateinit var detector : GestureDetector

    companion object {
        private const val SWIPE_MAX_OFF_PATH = 250
        private const val SWIPE_MIN_DISTANCE = 120
        private const val SWIPE_THRESHOLD_VELOCITY = 200;
    }

    private val viewModel : MealViewModel by lazy {
        getViewModel(MealViewModel::class)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_meal

    override fun observeEvent() {
        with(viewModel){
            previousClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                date.value = spDateFormat("YYYY년 MM월 dd일", --progress)
                getMeal()
            })

            nextClick.observe(this@MealFragment, androidx.lifecycle.Observer {
                date.value = spDateFormat("YYYY년 MM월 dd일", ++progress)
                getMeal()
            })

            onErrorEvent.observe(this@MealFragment, EventObserver {
                Log.e("MYTAG", it)
            })

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel, viewModel)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onResume() {
        super.onResume()
        detectorSetting()
        binding.view.setOnTouchListener { v, event ->
            detector.onTouchEvent(event)
            true
        }

        viewModel.getMeal()

    }

    private fun detectorSetting() {
        detector = GestureDetector(context, object : GestureDetector.OnGestureListener {
            override fun onDown(e: MotionEvent?): Boolean = true
            override fun onShowPress(e: MotionEvent?) { }
            override fun onSingleTapUp(e: MotionEvent?): Boolean = true
            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean = true
            override fun onLongPress(e: MotionEvent?) { }
            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                try {
                    if(abs(e1!!.y - e2!!.y) > SWIPE_MAX_OFF_PATH) return false

                    // right to left swipe
                    if (e1.x - e2.x > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        Log.d("MYTAG", "LEFT")
                        viewModel.date.value = spDateFormat("YYYY년 MM월 dd일", ++viewModel.progress)
                        viewModel.getMeal()
                    }
                    // left to right swipe
                    else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                        Log.d("MYTAG", "RIGHT")
                        viewModel.date.value = spDateFormat("YYYY년 MM월 dd일", --viewModel.progress)
                        viewModel.getMeal()
                    }

                } catch (e : Exception) {
                    e.printStackTrace()
                }
                return true
            }

        })


    }

}