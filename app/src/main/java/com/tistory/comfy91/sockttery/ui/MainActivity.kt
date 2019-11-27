package com.tistory.comfy91.sockttery.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tistory.comfy91.sockttery.R
import com.tistory.comfy91.sockttery.api.ServerService
import com.tistory.comfy91.sockttery.api.enqueue
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.reward_dialog.view.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var count : Int = 0
    var totalCount : Int =0
    val service = ServerService.service
    var userId = "Merry1225"
    internal val mRunnable = Runnable {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.reward_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val mDialog = mBuilder.show()
        mDialogView.img_close.setOnClickListener{
            mDialog.dismiss()
        }
        mDialogView.btn_confirm.setOnClickListener{
            val intent = Intent(this,MyPageActivity::class.java)
            startActivity(intent)
            mDialog.dismiss()
        }
        mDialogView.btn_continue.setOnClickListener{
            mDialog.dismiss()
        }
        mDialogView.btn_jarang.setOnClickListener{
            shareWithOtherApp(1,"최호준")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun setCount(){
        count = Random.nextInt(10)
        totalCount = count
    }
    private fun init(){
        setClickEvent()
        setCount()
        setAnimToAllSnow()
        setAnimToMainTitle()
    }
    private fun setAnimToAllSnow(){
        setAnimToSnow(snow1)
        setAnimToSnow(snow2)
        setAnimToSnow(snow3)
        setAnimToSnow(snow4)
        setAnimToSnow(snow5)
        setAnimToSnow(snow6)
        setAnimToSnow(snow7)
        setAnimToSnow(snow8)
        setAnimToSnow(snow9)
        setAnimToSnow(snow10)
        setAnimToSnow(snow09)
    }
    private fun setAnimToSnow(imageView: ImageView){
        val duration = Random.nextLong(2000,4000)
        val fade = ObjectAnimator.ofFloat(imageView, View.ALPHA, 1.0f, 0f)
        fade.setDuration(duration)
        fade.repeatCount = ObjectAnimator.INFINITE
        val tx = ValueAnimator.ofFloat(0f, 300f)
        val mDuration = duration //in millis
        tx.repeatCount = ObjectAnimator.INFINITE
        tx.duration = mDuration.toLong()
        tx.addUpdateListener { animation -> imageView!!.setTranslationY(animation.animatedValue as Float) }
        val animset = AnimatorSet()
        animset.playTogether(fade,tx)
        animset.start()
    }

    private fun setAnimToMainTitle(){
        val anims = AnimatorSet();
        val sX = ObjectAnimator.ofFloat(mainTitle, View.SCALE_X, 0.9f, 0.91f)
        val sY = ObjectAnimator.ofFloat(mainTitle, View.SCALE_Y, 0.9f, 0.91f)
        sX.repeatCount = ValueAnimator.INFINITE
        sY.repeatCount = ValueAnimator.INFINITE
        anims.duration=800
        anims.playTogether(sX, sY)
        anims.start()
    }
    private fun setClickEvent(){
        val img_sock_left_top = findViewById<ImageView>(R.id.img_sock_left_top)
        val img_sock_right_top = findViewById<ImageView>(R.id.img_sock_right_top)
        val img_sock_left_mid = findViewById<ImageView>(R.id.img_sock_left_mid)
        val img_sock_right_mid = findViewById<ImageView>(R.id.img_sock_right_mid)
        val img_sock_left_bottom = findViewById<ImageView>(R.id.img_sock_left_bottom)
        val img_sock_right_bottom = findViewById<ImageView>(R.id.img_sock_right_bottom)
        img_sock_left_top.setOnClickListener{
            fall_animation(img_sock_left_top)
            countByClick(img_sock_left_top)
        }
        img_sock_right_top.setOnClickListener{
            fall_animation(img_sock_right_top)
            countByClick(img_sock_right_top)
        }
        img_sock_left_mid.setOnClickListener{
            fall_animation(img_sock_left_mid)
            countByClick(img_sock_left_mid)
        }
        img_sock_right_mid.setOnClickListener{
            fall_animation(img_sock_right_mid)
            countByClick(img_sock_right_mid)
        }
        img_sock_left_bottom.setOnClickListener{
            fall_animation(img_sock_left_bottom)
            countByClick(img_sock_left_bottom)
        }
        img_sock_right_bottom.setOnClickListener{
            fall_animation(img_sock_right_bottom)
            countByClick(img_sock_right_bottom)
        }

        img_mypage.setOnClickListener{
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
    private fun countByClick(img_socks : ImageView){
        if(count==0){
            sendToast("당첨")
            setCount()
            fall_socks(img_socks)
            val delayEvent = Handler()
            delayEvent!!.postDelayed(mRunnable,800)

            //서버로 당첨자 아이디 요청
            /*
            service.reqRecommend().enqueue(
                onResponse = {response ->
                    if(response.isSuccessful){
                        if(userId.equals(response.body()!!.data.userId)){
                            sendToast("당첨")
                            setCount()
                            fall_socks(img_socks)
                            val mDialogView = LayoutInflater.from(this).inflate(R.layout.reward_dialog, null)
                            val mBuilder = AlertDialog.Builder(this)
                                .setView(mDialogView)
                            val mDialog = mBuilder.show()
                            mDialogView.img_close.setOnClickListener{
                                mDialog.dismiss()
                            }
                            mDialogView.btn_confirm.setOnClickListener{
                                mDialog.dismiss()
                            }
                            mDialogView.btn_continue.setOnClickListener{
                                mDialog.dismiss()
                            }
                            mDialogView.btn_jarang.setOnClickListener{
                                shareWithOtherApp(response.body()!!.data.userIdx,response.body()!!.data.userId)
                            }
                        }else{
                            sendToast("당첨안됨")
                            fall_socks(img_socks)
                            setCount()
                        }
                    }
                }
            )
             */

        }else{
            count--
            sendToast("조금만 더..!!")
        }
    }

    private fun sendToast(message:String){
        val toast = Toast.makeText(this,message,Toast.LENGTH_SHORT)
        toast.show()
    }
    private fun fall_animation(imageView: ImageView){

        val anims2 = AnimatorSet()
        val rotateRight1 = ObjectAnimator.ofFloat(imageView, View.ROTATION, 0f, 15f)
        rotateRight1.setDuration(150)
        val rotateLeft1 = ObjectAnimator.ofFloat(imageView, View.ROTATION, 15f, -15f)
        rotateLeft1.setDuration(200)
        val rotateRight2 = ObjectAnimator.ofFloat(imageView, View.ROTATION, -15f, 15f)
        rotateRight2.setDuration(200)
        val rotateLeft2 = ObjectAnimator.ofFloat(imageView, View.ROTATION, 15f, 0f)
        rotateLeft2.setDuration(150)
        anims2.playSequentially(rotateRight1,rotateLeft1,rotateRight2,rotateLeft2)
        val final_anim = AnimatorSet()
        final_anim.play(anims2)
        final_anim.start()
    }
    private fun fall_socks(imageView: ImageView){
        val anim = AnimatorSet()
        val ty1 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 0f, 100f)
        ty1.setDuration(1300)
        val ty2 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 100f, 200f)
        ty1.setDuration(600)
        //ty1.interpolator = BounceInterpolator()
        //ty1.start()
        val ty3 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 200f, 0f)
        ty3.setDuration(1)
        anim.playSequentially(ty1,ty2,ty3)
        val final_anim = AnimatorSet()
        final_anim.play(anim)
        final_anim.start()
    }


    fun shareWithOtherApp(money: Int, id: String){
        val text = "${money}원이 ${id}님에게 당첨됐터리 ʕ￫ᴥ￩ʔ Socks will be Lucks, 크리스마스엔 양말로 싹터리!"
        val imageUri: Uri = Uri.parse("android.resource://"+this@MainActivity.packageName+"/drawable/img_share")
        val shareIntent : Intent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
        shareIntent.setType("image/jpeg")
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "send"));
    }

}
