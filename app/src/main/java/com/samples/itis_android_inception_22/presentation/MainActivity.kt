package com.samples.itis_android_inception_22.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.data.repository.WeatherRepository
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import kotlinx.coroutines.launch

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = WeatherRepository()
        val city = "Moscow"
        lifecycleScope.launch {
            runCatching {
                repository.getWeatherInfoByCityName(city = city)
            }.onSuccess {
                Log.i("MainActivity", "$city temp: ${it?.main?.temp}")
            }.onFailure {
                Toast.makeText(this@MainActivity, "Error: $it", Toast.LENGTH_SHORT).show()
                Log.e("ExceptionOccurred", "$it")
            }
        }
    }
}