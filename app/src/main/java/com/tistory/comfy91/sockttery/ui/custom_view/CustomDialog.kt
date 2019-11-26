package com.tistory.comfy91.sockttery.ui.custom_view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle

import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import com.tistory.comfy91.sockttery.R

class CustomDialog(context: Context, title: String, balance: String, imgId: Int, btnString: String
, intBtn: Int, lineColor: Int, listener: View.OnClickListener) : Dialog(context) {
    private lateinit var txtDialogTitle: TextView
    private lateinit var imgDialog: ImageView
    private lateinit var edtMoney: EditText
    private lateinit var txtBalance: TextView
    private lateinit var btnConfirm: Button
    private lateinit var underLine: View


    private var strTitle = title
    private var intImgId = imgId
    private var strBalance = balance
    private var strConfirm = btnString
    private var intBtnNum = intBtn
    private var intLineColor = lineColor
    private var setListener = listener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)

        txtDialogTitle = findViewById(R.id.txtDialogTitle)
        imgDialog = findViewById(R.id.imgDialog)
        edtMoney = findViewById(R.id.edtMoney)
        txtBalance = findViewById(R.id.txtBalance)
        btnConfirm = findViewById(R.id.btnConfirm)
        underLine = findViewById(R.id.underLine)


        txtDialogTitle.setText(strTitle)
        imgDialog.setImageResource(intImgId)
        txtBalance.setText(strBalance)
        btnConfirm.setText(strConfirm)


    } // end onCretae()

    fun setBtnBackground(backId: Int){
        btnConfirm.setBackgroundResource(backId)
    }

    fun setUnderLineColor(underlineColor: Int){
        underLine.setBackgroundResource(underlineColor)
    }

    fun setListener(listener: View.OnClickListener){
        btnConfirm.setOnClickListener(listener)
    }





} // end class