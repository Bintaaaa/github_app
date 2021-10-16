package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.UserAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityFavoriteBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.helper.ConvertToList
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail.DetailUserActivity
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.util.OnItemClickCallback

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClick(data: User) {
                val detailIntent = Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR,data.avatarUrl)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.url)
                }
                startActivity(detailIntent)
            }

        })

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUser.adapter = adapter
        }

        viewModel.getAllFavorite()?.observe(this, {
            if (it != null){
                val list = ConvertToList.convert(it)
                adapter.setList(list)
            }

        })

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}