package com.app.ui.result

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.viewModels
import com.app.R
import com.app.bases.BaseActivity
import com.app.databinding.ActivityResultBinding
import com.app.ui.main.MainViewModel
import com.app.ui.sidenavigation.SideNavigationActivity

class ResultActivity : BaseActivity<ActivityResultBinding, MainViewModel>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun initBinding() = ActivityResultBinding.inflate(layoutInflater)

    override fun initViews(savedInstanceState: Bundle?) {
        val minTemp = intent.getDoubleExtra("minTemp", 0.0)
        val maxTemp = intent.getDoubleExtra("maxTemp", 0.0)
        val rainfall = intent.getDoubleExtra("rainfall", 0.0)
        val windGust = intent.getDoubleExtra("windGust", 0.0)
        val humidity3pm = intent.getIntExtra("humidity3pm", 0)
        val pressure9am = intent.getDoubleExtra("pressure9am", 0.0)

        val conditions = mutableListOf<String>()
        var willRain = true

        if (minTemp in 10.0..25.0) conditions.add("✔ MinTemp in 10–25°C")
        else {
            conditions.add("✘ MinTemp not in 10–25°C")
            willRain = false
        }

        if (maxTemp in 15.0..30.0) conditions.add("✔ MaxTemp in 15–30°C")
        else {
            conditions.add("✘ MaxTemp not in 15–30°C")
            willRain = false
        }

        if (rainfall > 0.5) conditions.add("✔ Rainfall > 0.5 mm")
        else {
            conditions.add("✘ Rainfall ≤ 0.5 mm")
            willRain = false
        }

        if (windGust in 35.0..70.0) conditions.add("✔ WindGust in 35–70 km/h")
        else {
            conditions.add("✘ WindGust not in 35–70 km/h")
            willRain = false
        }

        if (humidity3pm in 60..100) conditions.add("✔ Humidity3pm in 60–100%")
        else {
            conditions.add("✘ Humidity3pm not in 60–100%")
            willRain = false
        }

        if (pressure9am in 1000.0..1012.0) conditions.add("✔ Pressure9am in 1000–1012 hPa")
        else {
            conditions.add("✘ Pressure9am not in 1000–1012 hPa")
            willRain = false
        }

        mViewBinding.textPrediction.text = if (willRain) "Tomorrow Rain: YES" else "Tomorrow Rain: NO"

        mViewBinding.textReasons.text = conditions.joinToString("\n")

        // Set background
        mViewBinding.root.setBackgroundResource(
            if (willRain) R.drawable.ic_rainy_day else R.drawable.ic_suuny_day
        )
    }

    override fun addViewModelObservers() {}
    override fun attachListens() {}
}
