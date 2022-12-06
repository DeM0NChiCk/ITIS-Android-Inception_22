package com.samples.itis_android_inception_22.presentation.service

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.samples.itis_android_inception_22.presentation.utils.NotificationsHandler

class ForegroundServiceExample : Service() {

    private var notification: Notification? = null
    private val foregroundServiceId: Int = 88212

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (notification == null) {
            val action = Intent(this, ForegroundServiceExample::class.java).apply {
                putExtra("SERVICE_ACTION", SERVICE_ACTION_STOP)
            }
            notification = NotificationsHandler.createNotification(
                ctx = this,
                titleText = "Заголовок сервиса",
                message = "Сообщение внутри уведомления",
                actionIntent = action
            )
            startForeground(foregroundServiceId, notification)
        }
        intent?.extras?.let { bundle ->
            bundle.getString("SERVICE_ACTION")?.let { action ->
                when (action) {
                    SERVICE_ACTION_STOP -> {
                        stopForeground(true)
                        stopSelf()
                    }
                    else -> {}
                }
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        notification = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? = null

    companion object {
        const val SERVICE_ACTION_STOP = "stop"
    }
}