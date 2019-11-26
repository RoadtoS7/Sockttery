package com.tistory.comfy91.sockttery

import android.app.Application

class Application: Application(){
    init{
        INSTANCE = this
    }

    override fun onCreate() {
        prefs = SharedPreferences(applicationContext)
        super.onCreate()
    }

    companion object{
        lateinit var INSTANCE: com.tistory.comfy91.sockttery.Application
        lateinit var prefs: SharedPreferences
    }
}