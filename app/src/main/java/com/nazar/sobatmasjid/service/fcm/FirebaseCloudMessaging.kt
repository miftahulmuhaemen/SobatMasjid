package com.nazar.sobatmasjid.service.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.nazar.sobatmasjid.R
import com.nazar.sobatmasjid.ui.activities.main.MainActivity
import io.karn.notify.Notify

class FirebaseCloudMessaging : FirebaseMessagingService() {

    companion object {
        private val TAG = FirebaseCloudMessaging::class.java.simpleName
    }

    override fun onNewToken(s: String) {
        super.onNewToken(s)
        Log.d(TAG, "Refreshed token: $s")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            sendNotification(it.title, it.body)
        }
    }

    private fun sendNotification(messageTitle: String?, messageBody: String?) {
        Notify
            .with(applicationContext)
            .meta {
                clickIntent = PendingIntent.getActivity(applicationContext,
                    0,
                    Intent(applicationContext, MainActivity::class.java),
                    0)
            }
            .content {
                title = messageTitle
                text = messageBody
            }
            .show()
    }

}