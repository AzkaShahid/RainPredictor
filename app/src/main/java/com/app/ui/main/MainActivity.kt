package com.app.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.app.ui.sidenavigation.SideNavigationActivity
import com.app.databinding.ActivityMainBinding
import com.app.bases.BaseActivity
import com.app.ui.result.ResultActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {
        // nothing needed here
    }

    override fun addViewModelObservers() {}

    override fun attachListens() {
        mViewBinding.btnPredict.setOnClickListener {
            if (validateInputs()) {
                val minTemp = mViewBinding.editMinTemp.text.toString().toDouble()
                val maxTemp = mViewBinding.editMaxTemp.text.toString().toDouble()
                val rainfall = mViewBinding.editRainfall.text.toString().toDouble()
                val windGust = mViewBinding.editWindGust.text.toString().toDouble()
                val humidity3pm = mViewBinding.editHumidity3pm.text.toString().toInt()
                val pressure9am = mViewBinding.editPressure9am.text.toString().toDouble()

                val intent = Intent(this, ResultActivity::class.java).apply {
                    putExtra("minTemp", minTemp)
                    putExtra("maxTemp", maxTemp)
                    putExtra("rainfall", rainfall)
                    putExtra("windGust", windGust)
                    putExtra("humidity3pm", humidity3pm)
                    putExtra("pressure9am", pressure9am)
                }
                startActivity(intent)
            }
        }
    }
    private fun validateInputs(): Boolean {
        var isValid = true

        val minTemp = mViewBinding.editMinTemp.text.toString().toDoubleOrNull()
        val maxTemp = mViewBinding.editMaxTemp.text.toString().toDoubleOrNull()
        val rainfall = mViewBinding.editRainfall.text.toString().toDoubleOrNull() ?: 0.0
        val windGust = mViewBinding.editWindGust.text.toString().toDoubleOrNull()
        val humidity3pm = mViewBinding.editHumidity3pm.text.toString().toIntOrNull()
        val pressure9am = mViewBinding.editPressure9am.text.toString().toDoubleOrNull()

        if (minTemp == null || minTemp !in 10.0..25.0) {
            mViewBinding.editMinTemp.error = "Enter MinTemp between 10–25°C"
            isValid = false
        }

        if (maxTemp == null || maxTemp !in 15.0..30.0) {
            mViewBinding.editMaxTemp.error = "Enter MaxTemp between 15–30°C"
            isValid = false
        }

        if (rainfall !in 0.0..4.0) {
            mViewBinding.editRainfall.error = "Enter rain fall between 0.0–4.0"
            isValid = false
        }

        if (windGust == null || windGust !in 35.0..70.0) {
            mViewBinding.editWindGust.error = "WindGust should be 35–70 km/h"
            isValid = false
        }

        if (humidity3pm == null || humidity3pm !in 60..100) {
            mViewBinding.editHumidity3pm.error = "Humidity should be 60–100%"
            isValid = false
        }

        if (pressure9am == null || pressure9am !in 1000.0..1012.0) {
            mViewBinding.editPressure9am.error = "Pressure should be 1000–1012 hPa"
            isValid = false
        }

        return isValid
    }


}

