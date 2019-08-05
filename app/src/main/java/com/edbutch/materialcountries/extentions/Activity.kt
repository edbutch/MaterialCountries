package com.edbutch.materialcountries.extentions

import android.app.Activity
import android.content.Intent
import com.edbutch.materialcountries.MapsActivity

fun Activity.displayActivity(activity: Class<*>) {
    startActivity(Intent(this, activity))
}