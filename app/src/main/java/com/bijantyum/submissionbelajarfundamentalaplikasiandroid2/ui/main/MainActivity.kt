package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityMainBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.UserAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail.DetailUserActivity
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.favorite.FavoriteActivity
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.menu.settings.SettingActivity
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.util.OnItemClickCallback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter

    private lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchView = binding.searchView
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : OnItemClickCallback {
            override fun onItemClick(data: User) {
                val detailIntent = Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                }
                startActivity(detailIntent)
            }

        })
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }

        searchUser()
        optionMenu()

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        viewModel.getSearchUsers().observe(this, {
            if (it != null) {
                adapter.setList(it)

            }
        })


    }

    private fun searchUser() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return if (query.isEmpty()) {
                    true
                } else {
                    viewModel.setSearchUsers(query)
                    true
                }
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }

        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            searchAnimation.visibility = View.GONE
            textLoadingAnimation.visibility = View.GONE
        }

    }

    private fun optionMenu(){
        val btnMenu = binding.btnMenu
        btnMenu.setOnClickListener {
            val popupMenu = PopupMenu(this,btnMenu)
            popupMenu.menuInflater.inflate(R.menu.option_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.favorite ->{
                      val intent =  Intent(this@MainActivity,FavoriteActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.settings ->{
                        val intent =  Intent(this@MainActivity, SettingActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
            popupMenu.show()
        }
    }


}

