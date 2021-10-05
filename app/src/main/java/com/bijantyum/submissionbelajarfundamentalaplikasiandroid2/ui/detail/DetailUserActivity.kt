package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val usernameDetail = intent.getStringExtra(EXTRA_USERNAME)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username = usernameDetail)
        viewModel.getUserDetail().observe(this,{
            if(it != null){
                binding.apply {
                    tvDetailUsername.text = it.login
                    tvDetailName.text = it.name
                    tvDetailCompany.text = it.company
                    tvDetailRepo.text = "${it.publicRepos}"
                    tvDetailLocation.text = it.location
                    tvDetailFollowers.text = "${it.followers}"
                    tvDetailFollowing.text = "${it.following}"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .into(ivUserPhoto)
                }
            }
        })

//        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
//        binding.apply {
//            viewPager.adapter = sectionPagerAdapter
//            tabs.setupWithViewPager(viewPager)
//        }
    }



    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }
}