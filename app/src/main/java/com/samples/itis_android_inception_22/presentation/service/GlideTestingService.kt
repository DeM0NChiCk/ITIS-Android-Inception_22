package com.samples.itis_android_inception_22.presentation.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.os.IBinder
import androidx.annotation.RequiresPermission
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideTestingService : Service() {

    private var glideInstance: RequestManager? = null
    private var locationManager: LocationManager? = null

    override fun onCreate() {
        super.onCreate()
        if (glideInstance == null) {
            glideInstance = Glide.with(this)
        }
        if (locationManager == null) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
    }

    @RequiresPermission(android.Manifest.permission.ACCESS_FINE_LOCATION)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        glideInstance
            ?.load("https://img.gazeta.ru/files3/397/14400397/chmonya-pic_32ratio_900x600-900x600-7396.jpg")
            ?.addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    println("TEST TAG - Resource ready")
                    return false
                }
            })
            ?.preload()

        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000L, 1f) {
            println("TEST TAG - new location: ${it.latitude}")
        }


        return START_STICKY
    }

    override fun onDestroy() {
        glideInstance = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? = null
}