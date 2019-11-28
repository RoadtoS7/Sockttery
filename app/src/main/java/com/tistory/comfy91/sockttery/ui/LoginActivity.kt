package com.tistory.comfy91.sockttery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tistory.comfy91.sockttery.R
import com.tistory.comfy91.sockttery.api.ServerService
import com.tistory.comfy91.sockttery.data.ResSignIn
import com.tistory.comfy91.sockttery.data.ResSignUp
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    val REQUEST_CODE_LOGIN_ACTIVITY = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            val edtId: String = edt_id.text.toString()
            val edtPw: String = edt_password.text.toString()
            if (edtId == "") edt_id.requestFocus()
            else if(edtPw == "") edt_password.requestFocus()
            else {
                val intent: Intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Log.d(this::class.java.name, "Before Call")
                val call: Call<ResSignIn> = ServerService.service.resSignIn(edtId, edtPw)
                call.enqueue(
                    object : Callback<ResSignIn> {
                        override fun onFailure(call: Call<ResSignIn>, t: Throwable) {
                            Log.e(this::class.java.name, "network error : $t")
                        }

                        override fun onResponse(
                            call: Call<ResSignIn>,
                            response: Response<ResSignIn>
                        ) {
                            if (response.isSuccessful) {
                                val ResSingIn = response.body()!!

                                val intent: Intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()

                                Toast.makeText(this@LoginActivity, "$ResSingIn", Toast.LENGTH_LONG).show()
                            }
                            else {
                                Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_LONG).show()

                            }
                        }
                    }
                )
            }
            //val serverservice=ServerService.service
            //serverservice.resSignIn(edtId , edtPw)
//            val call: Call<ResSignIn> = ServerService.service.resSignIn(edtId, edtPw)

//            call.enqueue(
//                object : Callback<ResSignIn> {
//                    override fun onFailure(call: Call<ResSignIn>, t: Throwable) {
//                        Log.e(this::class.java.name, "network error : $t")
//                    }
//
//                    override fun onResponse(
//                        call: Call<ResSignIn>,
//                        response: Response<ResSignIn>
//                    ) {
//                        if (response.isSuccessful) {
//                            val ResSingIn = response.body()!!
//
//                            Toast.makeText(this@LoginActivity, "$ResSingIn", Toast.LENGTH_LONG).show()
//                        }
//                        else {
//                            Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_LONG).show()
//                        }
//                    }
//                }
//            )
        }

        btn_register.setOnClickListener {
            val intent: Intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent,REQUEST_CODE_LOGIN_ACTIVITY)

        }
//        init()
    }
//    private fun init(){
//        btn_login.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }
}
