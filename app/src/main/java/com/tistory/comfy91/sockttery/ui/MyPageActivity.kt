package com.tistory.comfy91.sockttery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.tistory.comfy91.sockttery.data.preferences.Application
import com.tistory.comfy91.sockttery.R

class MyPageActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private lateinit var userId: String
    private lateinit var tvId: TextView
    private lateinit var tvMoney: TextView
    private var prev: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        setView()
        val btnInputMoney = findViewById<Button>(R.id.btnInputMoney)
        val btnOutputMoney = findViewById<Button>(R.id.btnOutMoney)

        val moneyListener = View.OnClickListener { v->
            val button =  v as Button
            if(button.id == R.id.btnInputMoney){

            }
            else if(button.id == R.id.btnOutMoney){

            }
            else{
                Log.d(TAG, "Wrong Button Id")
            }
        } // end moneyListener

        btnInputMoney.setOnClickListener(moneyListener)
        // todo id값 얻어와야한다.
        userId = "k"
        setSPId(userId) // sharedPreferences id 설정
        setPrevMoney()


        tvId.setText(userId) // id표시
        tvMoney.setText(prev.toString()) // 돈표시




    } // end onCreate()

    private fun setView(){
        tvId= findViewById(R.id.tvId)
        tvMoney = findViewById(R.id.tvMoney)

    }

    private fun setPrevMoney(){
        if(!(Application.prefs.myMoney.isNullOrBlank())) {
            val str = (Application.prefs.myMoney)
            prev = str!!.toInt()
        }

    }

    fun setSPId(id: String){
        Application.prefs.setUserId(id)
    }
} // end class
