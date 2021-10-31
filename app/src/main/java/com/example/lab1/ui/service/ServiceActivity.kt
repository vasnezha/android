package com.example.lab1.ui.service

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lab1.R
import com.example.lab1.service.RandomNumberService
import com.google.android.material.button.MaterialButton

class ServiceActivity : AppCompatActivity() {

    private lateinit var materialButtonStartService: MaterialButton
    private lateinit var materialButtonStopService: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        materialButtonStartService = findViewById(R.id.materialButtonStartService)
        materialButtonStopService = findViewById(R.id.materialButtonStopService)

        val serviceClass = RandomNumberService::class.java

        val intent = Intent(this, serviceClass)

        materialButtonStartService.setOnClickListener {
            // Запускаем службу
            // If the service is not running then start it
            if (!isServiceRunning(serviceClass)) {
                startService(intent)
            } else {
                toast("Service already running.")
            }
        }

        materialButtonStopService.setOnClickListener {
            // Останавливаем службу
            if (isServiceRunning(serviceClass)) {
                stopService(intent)
            } else {
                toast("Service already stopped.")
            }
        }
    }
    // Custom method to determine whether a service is running
    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }
}

// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
}