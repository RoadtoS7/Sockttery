package com.tistory.comfy91.sockttery.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tistory.comfy91.sockttery.R
import com.tistory.comfy91.sockttery.data.my_page.DummyMyPageRepository
import com.tistory.comfy91.sockttery.data.charge.ResCharge
import com.tistory.comfy91.sockttery.data.my_page.ResMyPage
import com.tistory.comfy91.sockttery.data.charge.DummyChargeRepository
import com.tistory.comfy91.sockttery.data.get_reward.DummyGetRewardRepository
import com.tistory.comfy91.sockttery.data.get_reward.ResGetReward
import com.tistory.comfy91.sockttery.data.preferences.Application
import com.tistory.comfy91.sockttery.ui.custom_view.CustomDialog
import kotlinx.android.synthetic.main.activity_my_page.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName // 태그

    private lateinit var tvId: TextView
    private lateinit var tvMoney: TextView

    private lateinit var customInputDialog: CustomDialog
    private lateinit var customOutDialog: CustomDialog

    private var prev: Int = 0
    private var userIdx: Int = 0 // index 0이면 값 안 온 것
    private lateinit var userId: String
    private lateinit var userPw: String
    private lateinit var money: String
    private  var pay: Int = -1 // -1 이면 값 안 온 것
    private var tempMoney: String? = null

    // 서버 통신
    val myPageRepository = DummyMyPageRepository()
    val getRewardRepository = DummyGetRewardRepository()
    val chargeRepository = DummyChargeRepository()

    private val dialogListener = View.OnClickListener {
        tempMoney = customInputDialog.getEdtMoneyText()
        if(!TextUtils.isEmpty(tempMoney)){
            reqCharge(userIdx.toString(), tempMoney!!)
        }
        else{
            Toast.makeText(this@MyPageActivity, "금액을 입력해주세요.", Toast.LENGTH_LONG).show()
        }


    }

    val outListener = View.OnClickListener {
        tempMoney = customOutDialog.getEdtMoneyText()
        if(!TextUtils.isEmpty(tempMoney)){
            reqpPayMoney(userIdx.toString(), tempMoney!!)
        }
        else{
            Toast.makeText(this@MyPageActivity, "금액을 입력해주세요.", Toast.LENGTH_LONG).show()
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        init()

        getMyPageData() // from  서버 마이페이지 구성 데이터 가져옴


        customInputDialog = CustomDialog(this@MyPageActivity, "충전하기", money, R.drawable.import_icon, "충전하기",
            R.drawable.btn_green, R.drawable.btn_green, dialogListener)
        customOutDialog = CustomDialog(this@MyPageActivity, "출금하기", money, R.drawable.money_icon, "출금하기",
            R.drawable.custom_dialog_confirm, R.drawable.red_line, outListener)


        val moneyListener = View.OnClickListener { v->
            val button =  v as Button
            if(button.id == R.id.btnInputMoney){
                customInputDialog.show()
            }
            else if(button.id == R.id.btnOutMoney){
                customOutDialog.show()
            }
            else{
                Log.d(TAG, "Wrong Button Id")
            }
        } // end moneyListener

        btnInputMoney.setOnClickListener(moneyListener)
        btnOutMoney.setOnClickListener(moneyListener)

    } // end onCreate()




    // 초기화
    private fun init(){
        setView() // 뷰설정
        userId = "happy1"
        setSPId(userId) // sharedPreferences id 설정
        setPrevMoney()
    }

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


    fun getMyPageData(){
        val call = myPageRepository.reqMyPage(userId)
        call.enqueue(object : Callback<ResMyPage>{
            override fun onFailure(call: Call<ResMyPage>, t: Throwable) {
                Log.d(TAG, "Fail Request My Page, Message: ${t.message}")
                Log.d(TAG, call.toString())
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

            })

    } // end getMyPageData()

    fun reqCharge(userIdx: String, chargeMoney: String){
        val call : Call<ResCharge> = chargeRepository.reqCharge(userIdx, chargeMoney)
        call.enqueue(object : Callback<ResCharge>{
            override fun onFailure(call: Call<ResCharge>, t: Throwable) {
                Log.d(TAG, "Fail to Request Charge, message: ${t.message}")

            }

            override fun onResponse(call: Call<ResCharge>, response: Response<ResCharge>) {
                val PLUS = true
                if(response.isSuccessful){
                    Log.d(TAG, "Success Request Charge")
                    val charge = response.body()
                    Toast.makeText(this@MyPageActivity, "입금 완료되었습니다.", Toast.LENGTH_LONG).show()
                    moneyChange(tempMoney!!, PLUS)
                    tvMoney.text = money
                    customInputDialog.dismiss()
                }
                else{
                    Log.d(TAG, "Response is Null")

                }
            }

        })

    }

    fun reqpPayMoney(idx: String, reward: String){
        val call = getRewardRepository.reqGetReward(userId, reward)
        call.enqueue(object : Callback<ResGetReward>{
            override fun onFailure(call: Call<ResGetReward>, t: Throwable) {
                Log.d(TAG, "Fail to Request 출금하기: ${t.message}")
            }

            override fun onResponse(call: Call<ResGetReward>, response: Response<ResGetReward>) {
                val MINUS = false
                if(response.isSuccessful){
                    val res = response.body()
                    Log.d(TAG, "Success Request 출금하기, Message: ${res!!.message}")
                    moneyChange(tempMoney!!, MINUS)
                    Toast.makeText(this@MyPageActivity, "출금완료되었습니다.", Toast.LENGTH_LONG).show()
                    tvMoney.text = money
                    customOutDialog.dismiss()

                }
                else{
                    Log.d(TAG, "Success Request 출금하기 but Response is Null")

                }
            }
        })
    }

    private fun moneyChange(howMuch: String, flag: Boolean){
        if(flag){
            plusMoney(howMuch)
        }
        else{
            minusMoney(howMuch)
        }

    }

    private fun plusMoney(plus: String){
        money = (money.toInt() + plus.toInt()).toString()
    }
    private fun minusMoney(minus: String){
        money = (money.toInt() - minus.toInt()).toString()
    }




} // end class
