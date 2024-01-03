package com.example.myapplication_10910247

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class myservice : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var lightsensor: Sensor

    override fun onBind(intent: Intent): IBinder {
        return null

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        sensorManager=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lightsensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
        sensorManager.registerListener(this,lightsensor,1000000,1000000)

        CoroutineScope(Dispatchers.IO).launch {
            delay(3000)
            makeMotify("客戶")
        }
        return START_NOT_STICKY
    }

    private fun makeMotify(s: String) {
        val notifyManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel("mCounter","Channel Counter,",NotificationManager.IMPORTANCE_HIGH)

    }


    override fun onSensorChanged(p0: SensorEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }
}