package com.example.lab1.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import java.util.*

class RandomNumberService: Service() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Send a notification that service is started
        Toast.makeText(this, "Service started.", Toast.LENGTH_SHORT).show()

        // Do a periodic task
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable { showRandomNumber() }
        handler.postDelayed(runnable, 2000)

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service destroyed.", Toast.LENGTH_SHORT).show()
        handler.removeCallbacks(runnable)
    }

    // Custom method to do a task
    private fun showRandomNumber() {
        val rand = Random()
        val number = rand.nextInt(100)
        Toast.makeText(this, "Random Number : $number", Toast.LENGTH_SHORT).show()
        handler.postDelayed(runnable, 5000)
    }
}