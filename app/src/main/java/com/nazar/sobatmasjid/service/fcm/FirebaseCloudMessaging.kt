package com.nazar.sobatmasjid.service.fcm

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavDeepLinkBuilder
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
            sendNotification(it.title, it.body, it.channelId)
        }
    }

    private fun sendNotification(messageTitle: String?, messageBody: String?, channelId: String?) {
        Notify
            .with(applicationContext)
            .meta {
                clickIntent = NavDeepLinkBuilder(applicationContext)
                    .setComponentName(MainActivity::class.java)
                    .setGraph(R.navigation.nav_graph_main)
                    .setDestination(R.id.mosqueDetail)
                    .setArguments(Bundle().apply {
                        putString("id", channelId)
                    })
                    .createPendingIntent()
            }
            .content {
                title = messageTitle
                text = messageBody
            }
            .show()
    }

}