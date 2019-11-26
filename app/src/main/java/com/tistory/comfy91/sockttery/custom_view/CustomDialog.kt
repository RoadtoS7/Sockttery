package com.tistory.comfy91.sockttery.custom_view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.tistory.comfy91.sockttery.R

class CustomDialog(context: Context, title: String, imgId: Int) : Dialog(context) {
    private lateinit var txtDialogTitle: TextView
    private lateinit var imgDialog: ImageView
    private lateinit var btnConfirm: Button
    private lateinit var edtMoney: EditText

    private  var strTitle = title


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)

        txtDialogTitle = findViewById(R.id.txtDialogTitle)
        imgDialog = findViewById(R.id.imgDialog)
        btnConfirm = findViewById(R.id.btnConfirm)
        edtMoney = findViewById(R.id.edtMoney)


        txtDialogTitle.setText(strTitle)





    } // end onCretae()




} // end class