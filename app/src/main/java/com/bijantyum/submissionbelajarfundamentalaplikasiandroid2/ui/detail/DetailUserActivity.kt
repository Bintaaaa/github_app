package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.SectionPagerAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val usernameDetail = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,usernameDetail)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username = usernameDetail)
        viewModel.getUserDetail().observe(this,{
            if(it != null){
                binding.apply {
                    tvDetailUsername.text = it.login
                    tvDetailName.text = it.name
                    tvDetailCompany.text = it.company
                    tvDetailRepo.text = "${it.following}"
                    tvDetailLocation.text = it.location
                    tvDetailFollowers.text = "${it.followers}"
                    tvDetailFollowing.text = "${it.publicRepos}"
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .centerCrop()
                        .into(ivUserPhoto)
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager){ tab,position ->
        tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }



    companion object{
        const val EXTRA_USERNAME = "extra_username"
            @StringRes
            private val TAB_TITLES = intArrayOf(
                R.string.followers,
                R.string.following
            )
    }
}