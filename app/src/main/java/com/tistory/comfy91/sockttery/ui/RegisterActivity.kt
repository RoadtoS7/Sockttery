package com.tistory.comfy91.sockttery.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tistory.comfy91.sockttery.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_ok.setOnClickListener {
            val edtId: String = edt_id.text.toString()
            val edtPw: String = edt_password.text.toString()
            if (edtId == "") edt_id.requestFocus()
            else if (edtPw == "") edt_password.requestFocus()
            else {
                postRegisterResponse(edtId, edtPw)
                finish()
            }
        }
    }
    fun postRegisterResponse(user_id: String, user_password: String) {
        val intent: Intent = Intent()
        //intent.putExtra("login", user)
        setResult(Activity.RESULT_OK, intent)
        //값 넘기기

        finish()
    }
}
