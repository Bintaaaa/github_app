package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.SectionPagerAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityDetailUserBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.util.loadImage
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val usernameDetail = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatar = intent.getStringExtra(EXTRA_AVATAR)
        val url = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        var _isChecked = false
        bundle.putString(EXTRA_USERNAME,usernameDetail)

        viewModel = ViewModelProvider(this).get(DetailUserViewModel::class.java)
        viewModel.setUserDetail(username = usernameDetail)
        viewModel.getUserDetail().observe(this,{
            if(it != null){
                binding.apply {
                    tvDetailUsername.text = it.login
                    tvDetailName.text = it.name ?: "-"
                    tvDetailCompany.text = it.company ?: "No Company"
                    tvDetailRepo.text = "${it.following}"
                    tvDetailLocation.text = it.location ?: "No Location"
                    tvDetailFollowers.text = "${it.followers}"
                    tvDetailFollowing.text = "${it.publicRepos}"
                    ivUserPhoto.loadImage(it.avatarUrl)
                }
            }

            btnShare(it.url)
        })
        viewModel.isLoading.observe(this,{
            showLoading(it)
        })

       viewPager(bundle)

        btnBack()

        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count > 0){
                        binding.toggleFavorite.isChecked = true
                        _isChecked = true
                    }else{
                        binding.toggleFavorite.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.toggleFavorite.setOnClickListener {
            _isChecked = !_isChecked
            if (_isChecked){
                viewModel.addToFavorite(id = id, username = usernameDetail, avatar =  avatar, url = url)
            }else{
                viewModel.removeFromFavorite(id = id)
            }
            binding.toggleFavorite.isChecked = _isChecked
        }
    }


    private fun viewPager(bundle: Bundle){
        val sectionPagerAdapter = SectionPagerAdapter(this,bundle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager){ tab,position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun btnBack(){
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun btnShare(url: String){
        binding.btnShared.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, url)
            intent.type = "text/plain"
            startActivity(intent)
        }
    }

    private fun showLoading(isLoading: Boolean){
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"
        const val EXTRA_URL = "extra_url"

            @StringRes
            private val TAB_TITLES = intArrayOf(
                R.string.followers,
                R.string.following
            )
    }
}