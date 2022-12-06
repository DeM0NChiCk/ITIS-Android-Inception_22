package com.samples.itis_android_inception_22.presentation.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.samples.itis_android_inception_22.R

object NotificationsHandler {

    private const val DEFAULT_NOTIFICATION_CHANNEL_ID = "DefaultNotificationChannel"
    private const val FIRST_ACTION_REQUEST_CODE = 1224

    fun createNotification(
        ctx: Context,
        titleText: String,
        message: String,
        actionIntent: Intent? = null
    ): Notification {
        val notificationBuilder = NotificationCompat.Builder(ctx, DEFAULT_NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_home_black)
            .setContentTitle(titleText)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        actionIntent?.let { firstIntent ->
            val pendingIntent = PendingIntent.getService(
                ctx,
                FIRST_ACTION_REQUEST_CODE,
                firstIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            val buttonText = ctx.getString(R.string.stop_execution)
            notificationBuilder.addAction(R.drawable.ic_add_task_black, buttonText, pendingIntent)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(ctx)
        }
        return notificationBuilder.build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(ctx: Context) {
        val notificationManager =
            ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (notificationManager.getNotificationChannel(DEFAULT_NOTIFICATION_CHANNEL_ID) == null) {
            val channelName = ctx.getString(R.string.notification_channel_name)
            val channelDescription =
                ctx.getString(R.string.notification_channel_description)
            val channel = NotificationChannel(
                DEFAULT_NOTIFICATION_CHANNEL_ID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                this.description = channelDescription
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
}