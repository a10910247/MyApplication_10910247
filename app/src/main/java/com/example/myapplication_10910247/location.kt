package com.example.myapplication_10910247

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat

class location() : AppCompatActivity(),LocationListener {

    private lateinit var tv1:TextView
    private lateinit var tv2:TextView
    private lateinit var tv3:TextView
    private lateinit var btnback:Button
    private lateinit var locationManager: LocationManager

    private var hasGps:Boolean=false
    private var hasNetwork:Boolean=false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        //GPS & Network
        hasGps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        var s = ""
        if (hasGps) s += "有GPS\n"
        if (hasNetwork) s += "有網路定位"

        tv1.text = s

        //登記Location & GPS
        if (hasGps || hasNetwork) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1.0F, this)
        } else
            tv2.text = "設備沒有位置"
    }

    override fun onPause() {
        super.onPause()
        locationManager.removeUpdates(this)
    }


    override fun onLocationChanged(p0: Location) {
        tv2.text=p0.longitude.toString()
        tv3.text=p0.latitude.toString()
    }
}