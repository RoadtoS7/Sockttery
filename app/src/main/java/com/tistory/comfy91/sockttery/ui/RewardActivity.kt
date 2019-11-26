package com.tistory.comfy91.sockttery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tistory.comfy91.sockttery.R
import kotlinx.android.synthetic.main.activity_reward.*


class RewardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reward)
        init()
    }
    private fun init(){
        lottie.setAnimation("giftbox.json")
        lottie.loop(true)
        lottie.playAnimation()
    }
}
