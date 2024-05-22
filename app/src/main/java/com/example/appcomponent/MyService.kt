package com.example.appcomponent

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast


// THis is Background Service
class MyService : Service() {
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

//    override fun onCreate() {
//        super.onCreate()
//    }


    // Members Implement
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showMassage()
        return super.onStartCommand(intent, flags, startId)
    }

    //To show massage
    private fun showMassage() {
        Handler(Looper.getMainLooper()).postDelayed({
            Toast.makeText(applicationContext, "THis Background service!!", Toast.LENGTH_SHORT).show()
        }, 5000)
    }

//    override fun onDestroy() {
//        super.onDestroy()
//    }
}