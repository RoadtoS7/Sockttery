package com.tistory.comfy91.sockttery.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.tistory.comfy91.sockttery.R
import com.tistory.comfy91.sockttery.api.ServerService
import com.tistory.comfy91.sockttery.api.enqueue
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var count : Int = 0
    var totalCount : Int =0
    val service = ServerService.service
    var userId = "Merry1225"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun setCount(){
        count = Random.nextInt(30)
        totalCount = count
    }
    private fun init(){
        setClickEvent()
        setCount()
        Glide.with(this).load(R.raw.background).into(img_background)
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
            //서버로 당첨자 아이디 요청
            service.reqRecommend().enqueue(
                onResponse = {response ->
                    if(response.isSuccessful){
                        if(userId.equals(response.body()!!.data.userId)){
                            sendToast("당첨")
                            setCount()
                            fall_socks(img_socks)
                            val intent = Intent(this,MyPageActivity::class.java)
                            startActivity(intent)
                        }else{
                            sendToast("당첨안됨")
                            setCount()
                        }
                    }
                }
            )

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
        val final_anim = AnimatorSet();
        final_anim.play(anims2)
        final_anim.start()
    }
    private fun fall_socks(imageView: ImageView){
        val ty1 = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_Y, 0f, 200f)
        ty1.setDuration(1000)
        ty1.interpolator = BounceInterpolator()
        ty1.start()
    }
}
