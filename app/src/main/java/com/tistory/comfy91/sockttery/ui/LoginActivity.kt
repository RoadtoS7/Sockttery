package com.tistory.comfy91.sockttery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tistory.comfy91.sockttery.R
import kotlinx.android.synthetic.main.activity_login.*

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
            }

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
