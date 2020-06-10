package com.example.ortalamahesapla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var asagidanGelenButon=AnimationUtils.loadAnimation(this,R.anim.asagidanyukarianim)

        btnAnim.animation=asagidanGelenButon

        var yukaridanGelenResim= AnimationUtils.loadAnimation(this,R.anim.yukaridanasaga)
        imageBalon.animation=yukaridanGelenResim


        var asagiGeriDonenButton=AnimationUtils.loadAnimation(this,R.anim.yukaridanasagi2)
        var yukariGeriDonenImage= AnimationUtils.loadAnimation(this,R.anim.asagidanyukarianim2)

        btnAnim.setOnClickListener{

            btnAnim.startAnimation(asagiGeriDonenButton)
            imageBalon.startAnimation(yukariGeriDonenImage)

            object : CountDownTimer(1000,1000){
                override fun onFinish() {

                    var intent= Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                }

                override fun onTick(millisUntilFinished: Long) {

                }


            }.start()

        }

    }
}
