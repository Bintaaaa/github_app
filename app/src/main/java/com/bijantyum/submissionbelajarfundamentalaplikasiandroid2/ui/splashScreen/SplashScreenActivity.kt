package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivitySplashScreeenBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreeenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreeenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
        }, DELAY)

    }

    companion object{
        private const val DELAY = 3000L
    }
}