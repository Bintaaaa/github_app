package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.UserAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.data.model.User
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.ActivityMainBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail.DetailUserActivity
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.util.OnItemClickCallback

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : OnItemClickCallback{
            override fun onItemClick(data: User) {
                val detailIntent = Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME,data.login)
                }
                startActivity(detailIntent)
            }

        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }

        searchListener()

        viewModel.isLoading.observe(this, {
            showLoading(it)
        })

        viewModel.getSearchUsers().observe(this,{
            if (it!=null){
                adapter.setList(it)

            }
        })



    }

    private fun searchUser(){
        binding.apply {
            val query = edtQuery.text.toString()
            if (query.isEmpty()) return
            viewModel.isLoading.observe(this@MainActivity, {
                showLoading(it)
            })
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            searchAnimation.visibility = View.GONE
            textLoadingAnimation.visibility = View.GONE
        }

    }

    private fun searchListener(){
        binding.apply {
            btnSearch.setOnClickListener {
                searchUser()
            }

            edtQuery.setOnKeyListener { _, i, keyEvent ->
                if(keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                    searchUser()
                    return@setOnKeyListener true
                }else

                    return@setOnKeyListener false
            }
        }
    }


}

