package com.tistory.comfy91.sockttery

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var count : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        setImageEvent()
        count = Random.nextInt(30)

    }
    private fun setImageEvent(){
        val img_sock_left_top = findViewById<ImageView>(R.id.img_sock_left_top)
        val img_sock_right_top = findViewById<ImageView>(R.id.img_sock_right_top)
        val img_sock_left_mid = findViewById<ImageView>(R.id.img_sock_left_mid)
        val img_sock_right_mid = findViewById<ImageView>(R.id.img_sock_right_mid)
        val img_sock_left_bottom = findViewById<ImageView>(R.id.img_sock_left_bottom)
        val img_sock_right_bottom = findViewById<ImageView>(R.id.img_sock_right_bottom)
        img_sock_left_top.setOnClickListener{
            fall_animation(img_sock_left_top)
            countByClick()
        }
        img_sock_right_top.setOnClickListener{
            fall_animation(img_sock_right_top)
            countByClick()
        }
        img_sock_left_mid.setOnClickListener{
            fall_animation(img_sock_left_mid)
            countByClick()
        }
        img_sock_right_mid.setOnClickListener{
            fall_animation(img_sock_right_mid)
            countByClick()
        }
        img_sock_left_bottom.setOnClickListener{
            fall_animation(img_sock_left_bottom)
            countByClick()
        }
        img_sock_right_bottom.setOnClickListener{
            fall_animation(img_sock_right_bottom)
            countByClick()
        }
    }
    private fun countByClick(){
        if(count==0){
            //서버로 당첨자 아이디 요청
            sendToast("당첨")
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
}
