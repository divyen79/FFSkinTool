package com.fastemoteskin.ffbundleskin.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.fastemoteskin.ffbundleskin.Activity.SplashActivity
import com.fastemoteskin.ffbundleskin.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        remoteMessage.notification?.let {
            Log.d("FCM_TOKEN", "Token title:${it.title}")
            Log.d("FCM_TOKEN", "Token body:${it.body}")
            showNotification(it.title ?: "Messages", it.body ?: "Instant alert received! Messages has just delivered your latest notification.")
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM_TOKEN", "Token onNewToken:${token}")

    }

    private fun showNotification(title: String, message: String) {
        val channelId = "default_channel"
        val intent = Intent(this, SplashActivity::class.java).apply {

            setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            putExtra("stop_service", true)
            putExtra("notification_id", 0)
        }
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.icon4) // your icon here
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH) // Required for heads-up (pre-Oreo)
            .setCategory(NotificationCompat.CATEGORY_CALL) // Required for heads-up
            .setContentIntent(pendingIntent)
            .setOngoing(false)
            .setAutoCancel(false)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setVibrate(longArrayOf(0, 300, 300, 300)) // Vibration pattern

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Default Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        manager.notify(0, builder.build())
    }
}