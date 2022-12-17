package com.samples.itis_android_inception_22.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.Options
import com.bumptech.glide.request.RequestOptions
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.db.DatabaseHandler
import com.samples.itis_android_inception_22.presentation.db.model.AddressModel
import com.samples.itis_android_inception_22.presentation.db.model.UserModel
import com.samples.itis_android_inception_22.presentation.service.ForegroundServiceExample
import com.samples.itis_android_inception_22.presentation.utils.PermissionsHandler
import kotlinx.coroutines.*

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private var requestPermission: PermissionsHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext?.let {
            DatabaseHandler.dbInitialize(appContext = it)
        }
    }
}