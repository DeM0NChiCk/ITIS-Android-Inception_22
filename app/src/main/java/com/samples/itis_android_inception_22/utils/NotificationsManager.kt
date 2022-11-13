package com.samples.itis_android_inception_22.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.presentation.MainActivity

class NotificationsManager(val ctx: Context) {

    fun createBasicNotification(notifId: Int = 101, text: String, message: String) {
        val manager = NotificationManagerCompat.from(ctx)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "DEFAULT_NOTIF_CHANNEL"
            val descriptionText = "Sample channel description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIFICATON_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            manager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(ctx, NOTIFICATON_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_settings_black)
            .setContentTitle(text)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Longer text version text version text version text version text version text version")

            )
            //.setContentIntent(createIntent())
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_details_black, "Text", createIntent()
            )
            .addAction(
                R.drawable.ic_home_black, "Text2", createIntent()
            )
            .addAction(
                R.drawable.ic_details_black, "Text3", createIntent()
            )
            .addAction(
                R.drawable.ic_settings_black, "Text4", createIntent()
            )
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(createIntent())
            .build()

        manager.notify(notifId, notificationBuilder)
    }

    private fun createIntent(): PendingIntent {
        val intent = Intent(ctx, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        //return PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        return PendingIntent.getActivity(
            ctx,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
    }

    companion object {
        private const val NOTIFICATON_CHANNEL_ID = "ZERO"
    }
}