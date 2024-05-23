package com.example.appcomponent

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast


// THis is Background / ForegroundService Service
class MyService : Service() {

    val service_ID = 1
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }


    //This ForegroundService Service
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channelId = "my_channel_1"
            val channel =
                NotificationChannel(channelId, "Default", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val notification =
                Notification.Builder(this, channelId).apply {
                    setContentTitle("notification title")
                    setContentText("this is content")
                    setSmallIcon(R.drawable.ic_launcher_foreground)
                }.build()

            //Some error here
            startForeground(service_ID, notification)
        }
    }


    // Members Implement
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showMessage()
        return super.onStartCommand(intent, flags, startId)
    }

    //To show message
    private fun showMessage() {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(
                applicationContext,
                "THis Foregroundservices service!!",
                Toast.LENGTH_SHORT
            ).show()
        }, 5000)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//    }
}