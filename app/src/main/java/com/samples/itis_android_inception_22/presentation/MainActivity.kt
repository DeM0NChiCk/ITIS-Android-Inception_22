package com.samples.itis_android_inception_22.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.ActivityMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.service.GlideTestingService
import com.samples.itis_android_inception_22.presentation.utils.PermissionsHandler

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)
    private var requestPermission: PermissionsHandler? = null

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermission = PermissionsHandler(activity = this) {
            val intent = Intent(this, GlideTestingService::class.java)
            startService(intent)
            println("TEST TAG - onPermissionGranted")
            /*val locationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                3000L,
                1f,
                {
                    println("TEST TAG - Updated in Activity: ${it.latitude} ${it.longitude}")
                },
                Looper.getMainLooper()
            )*/
        }

        with(viewBinding) {
            showImageBtn.setOnClickListener {
                Glide.with(this@MainActivity)
                    .load("https://img.gazeta.ru/files3/397/14400397/chmonya-pic_32ratio_900x600-900x600-7396.jpg")
                    .into(viewBinding.imageIv)
            }
            requestPermissionBtn.setOnClickListener {
                val check = checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                if (check == PackageManager.PERMISSION_GRANTED) {
                    val intent = Intent(this@MainActivity, GlideTestingService::class.java)
                    startService(intent)
                    /*val locationManager =
                        getSystemService(Context.LOCATION_SERVICE) as LocationManager
                    locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        3000L,
                        1f
                    ) {
                        println("TEST TAG - Updated in Activity: ${it.latitude} ${it.longitude}")
                    }*/
                } else {
                    requestPermission?.requestSinglePermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }
        }
    }
}