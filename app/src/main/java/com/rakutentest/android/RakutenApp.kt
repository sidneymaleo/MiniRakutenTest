package com.rakutentest.android

import android.app.Application
import com.google.android.material.color.DynamicColors

class RakutenApp: Application() {

    override fun onCreate() {
        super.onCreate()
        // Apply dynamic color
        //DynamicColors.applyToActivitiesIfAvailable
        DynamicColors.applyToActivitiesIfAvailable(this, R.style.AppTheme_Overlay)
    }

}