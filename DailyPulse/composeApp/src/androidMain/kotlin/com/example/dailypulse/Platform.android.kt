package com.example.dailypulse

import android.os.Build
import android.util.Log
import kotlin.math.round
import android.content.res.Resources
import kotlin.math.roundToInt

actual class Platform actual constructor() {
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        get() = round(Resources.getSystem().displayMetrics.density).roundToInt()

    actual fun logSystemInfo() {
        Log.d(
            "Daily Pulse",
            "OS Name: $osName, OS Version: $osVersion, Device Model: $deviceModel, Density: $density"
        )
    }

}