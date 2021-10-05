package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }
}