package com.example.appcomponent

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appcomponent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // INTENT To Call MY Services
        val intent = Intent(this, MyService::class.java)

        startService(intent)

        // TO SHOW SMS Massages
        getSms()


    }

    private fun getSms() {
        val uri = Uri.parse("content://sms//inbox")
        val projection = arrayOf(SMS_SENDER, SMS_BODY)
        val cursor: Cursor = contentResolver.query(uri, projection, null, null)!!
        while (cursor.moveToNext()) {
            for (i in 0 until cursor.getCount()) {
                Log.i(LOG_TAG, "$i - ${cursor.getColumnName(i)} - ${cursor.getString(i)}")
            }
        }
    }

    companion object {
        private const val LOG_TAG = "MAIN_SMS"
        private const val SMS_BODY = "body"
        private const val SMS_SENDER = "address"
    }
}