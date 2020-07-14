package com.zlk.akotlinlearn.common

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MyCrashHandler.getInstance().init(packageName)
        Thread.setDefaultUncaughtExceptionHandler(MyCrashHandler.getInstance())
    }

}