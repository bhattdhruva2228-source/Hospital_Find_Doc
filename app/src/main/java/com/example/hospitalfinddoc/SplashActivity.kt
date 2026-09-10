package com.example.hospitalfinddoc

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        val logo = findViewById<ImageView>(R.id.imgLogo)
        val tagline = findViewById<TextView>(R.id.tvTagline)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val loading = findViewById<TextView>(R.id.tvLoading)

        val logoAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.logo_animation
        )

        val fadeAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.fade_in
        )

        val slideAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.slide_up
        )


        logo.startAnimation(logoAnimation)


        tagline.postDelayed({
            tagline.startAnimation(fadeAnimation)
        }, 500)


        progressBar.postDelayed({
            progressBar.startAnimation(slideAnimation)
        }, 900)

        loading.postDelayed({
            loading.startAnimation(slideAnimation)
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(
                this,
                MainActivity::class.java
            )
            startActivity(intent)



        }, 2500)
    }
}