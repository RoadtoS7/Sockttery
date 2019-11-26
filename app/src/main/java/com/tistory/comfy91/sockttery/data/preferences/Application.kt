package com.tistory.comfy91.sockttery.data.preferences

import android.app.Application

class Application: Application(){
    init{
        INSTANCE = this
    }

    override fun onCreate() {
        prefs =
            SharedPreferences(applicationContext)
        super.onCreate()
    }

    companion object{
        lateinit var INSTANCE: com.tistory.comfy91.sockttery.data.preferences.Application
        lateinit var prefs: SharedPreferences
    }
}