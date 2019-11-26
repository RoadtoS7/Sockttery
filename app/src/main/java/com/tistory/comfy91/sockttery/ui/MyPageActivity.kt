package com.tistory.comfy91.sockttery.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tistory.comfy91.sockttery.R
import com.tistory.comfy91.sockttery.api.ServerService
import com.tistory.comfy91.sockttery.data.ResCharge
import com.tistory.comfy91.sockttery.data.ResMyPage
import com.tistory.comfy91.sockttery.data.preferences.Application
import com.tistory.comfy91.sockttery.ui.custom_view.CustomDialog
import kotlinx.android.synthetic.main.custom_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName

    private lateinit var tvId: TextView
    private lateinit var tvMoney: TextView
    private var prev: Int = 0
    private lateinit var customDialog: CustomDialog


    private var userIdx: Int = 0 // index 0이면 값 안 온 것
    private lateinit var userId: String
    private lateinit var userPw: String
    private lateinit var money: String
    private  var pay: Int = -1 // -1 이면 값 안 온 것

    private lateinit var customInputDialog: CustomDialog

    val inputListener = View.OnClickListener {
        reqCharge(userIdx.toString(), edtMoney.text.toString() )
        customInputDialog.dismiss()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        userId = "happy1"
        setSPId(userId) // sharedPreferences id 설정
        setPrevMoney()

        getMyPageData() // 서버 통신
//        testCustomDialog()
        setView()

        val btnInputMoney = findViewById<Button>(R.id.btnInputMoney)
        val btnOutputMoney = findViewById<Button>(R.id.btnOutMoney)

        money = "111"
        customDialog= CustomDialog(this@MyPageActivity, "충전하기", money, R.drawable.import_icon, "충전하기",
            R.drawable.btn_green, R.drawable.btn_green, inputListener)

        val outListener = View.OnClickListener {
//            reqOutMoney()
        }

        val moneyListener = View.OnClickListener { v->
            val button =  v as Button
            if(button.id == R.id.btnInputMoney){
                customDialog.show()
            }
            else if(button.id == R.id.btnOutMoney){
                var customOutView = CustomDialog(this@MyPageActivity, "출금하기", money, R.drawable.money_icon, "출금하기",
                    R.drawable.custom_dialog_confirm, R.drawable.red_line, outListener)
                customOutView.show()
            }
            else{
                Log.d(TAG, "Wrong Button Id")
            }
        } // end moneyListener

        btnInputMoney.setOnClickListener(moneyListener)
        // todo id값 얻어와야한다.



//        tvId.setText(userId) // id표시
//        tvMoney.setText(prev.toString()) // 돈표시




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

    fun testCustomDialog(){
//        customDialog = CustomDialog(this@MyPageActivity, "타이틀", "잔액", R.drawable.money_icon, "버튼 String")
//        customDialog.show()
    }

    fun getMyPageData(){
        Log.d(TAG, "Connection userID : ${userId}")
        val call: Call<ResMyPage> = ServerService.service.reqMyPage(userId)
        call.enqueue( object: Callback<ResMyPage>{
            override fun onFailure(call: Call<ResMyPage>, t: Throwable) {
                Log.d(TAG, "Fail Request My Page, Message: ${t.message}")
                Log.d(TAG, call.toString())
                money = "10"


                }

            override fun onResponse(call: Call<ResMyPage>, response: Response<ResMyPage>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Success Request My Page")

                    val myPage = response.body()
                    Log.d(TAG, myPage.toString())
                    userIdx = myPage!!.data!!.userIdx
                    userPw = myPage!!.data!!.password
                    money = myPage!!.data!!.money
                    pay = myPage!!.data!!.pay

                    tvId.setText("'${userId}'")
                    tvMoney.setText("${money}")


                }
                else{
                    Log.d(TAG, "Success Connection but Response is Null ")
                }


                } // end onResponse()

            }
        )
    } // end getMyPageData()

    fun reqCharge(userIdx: String, chargeMoney: String){
        val call : Call<ResCharge> = ServerService.service.reqCharge(userIdx, chargeMoney)
        call.enqueue(object : Callback<ResCharge>{
            override fun onFailure(call: Call<ResCharge>, t: Throwable) {
                Log.d(TAG, "Fail to Request Charge, message: ${t.message}")

            }

            override fun onResponse(call: Call<ResCharge>, response: Response<ResCharge>) {
                if(response.isSuccessful){
                    Log.d(TAG, "Success Request Charge")
                    val charge = response.body()
                    Toast.makeText(this@MyPageActivity, charge!!.message, Toast.LENGTH_LONG).show()
                }
                else{
                    Log.d(TAG, "Response is Null")

                }
            }

        })

    }

    fun reqChargeMoney(userIdx: String, chargeMoney: String){
//        val call: Call<ResCharge> = ServerService.service.reqCharge(chargeMoney)
//        call.enqueue(data )
    }

    fun<T> Call<T>.enqueue(
        onFailure : (Throwable) -> Unit,
        onRespons : (Response<T>) -> Unit
    ) {
        this.enqueue(object: Callback<T> {
            override fun onFailure(call: Call<T>, t: Throwable) {
                onFailure(t)
            }

            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                onRespons(response)
            }
        })
    }






} // end class
