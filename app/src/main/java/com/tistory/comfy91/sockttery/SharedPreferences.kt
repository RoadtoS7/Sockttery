package com.tistory.comfy91.sockttery

import android.content.Context
import android.content.SharedPreferences

private const val FILENAME = "SOCKTTERY"

class SharedPreferences (context: Context){
    private val prefs: SharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
    private var userId: String = ""

    // 예제와 달리 myMoney 를 nullable로 만듬
    var myMoney: String?
        get() = prefs.getString(userId, "")
        set(value) = prefs.edit().putString(userId, value).apply()

    fun setUserId(userId: String){
        this.userId = userId
    }
} // end class