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
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class myservice : Service(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var lightsensor: Sensor





    override fun onBind(p0: Intent?): IBinder? {
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
        notifyManager.createNotificationChannel(channel)
        val myBuilder=NotificationCompat.Builder(this,"mCounter").apply {
            setContentTitle("通知!!")
            setContentText("這是一個光感測器\n請 $s 注意")
            setSubText("光感測器")
            setWhen(System.currentTimeMillis())
            setChannelId("mCounter")
            setSmallIcon(R.drawable.library)

        }
        notifyManager.notify(1,myBuilder.build())

    }


    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0 !=null){
            if (p0.values[0]>=20000){
                val notifyManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val  channel = NotificationChannel("mCounter","Channel Counter",NotificationManager.IMPORTANCE_HIGH)
                val myBuilder=NotificationCompat.Builder(this,"mCounter").apply {
                    setContentTitle("感光通知!!")
                    setContentText("光線太強 ${p0.values[0]}\n 請注意!")
                    setSubText("光感測器")
                    setWhen(System.currentTimeMillis()-100)
                    setChannelId("mCounter")
                    setSmallIcon(R.drawable.library)

                }
                notifyManager.notify(1,myBuilder.build())
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        //TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }
}