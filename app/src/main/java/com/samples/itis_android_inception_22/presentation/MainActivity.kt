package com.samples.itis_android_inception_22.presentation

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.service.ForegroundServiceExample
import com.samples.itis_android_inception_22.presentation.utils.PermissionsHandler
import kotlinx.coroutines.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private var requestPermission: PermissionsHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Foreground service
        val intent = Intent(this, ForegroundServiceExample::class.java)
        ContextCompat.startForegroundService(this, intent)

        //  Coroutines
        lifecycleScope.launch {
            var counter = 0
            val jobsList = List(500) {
                async(Dispatchers.IO) { doSomething(++counter) }
            }
            jobsList.awaitAll()
        }
    }

    private suspend fun doSomething(name: Int) {
        delay(2500L)
        println("TEST TAG - My job is done: $name")
    }
}