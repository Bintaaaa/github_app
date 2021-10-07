package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.followers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.UserAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.FragmentDetailFollowersBinding
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail.DetailUserActivity

class FollowersFragment: Fragment(R.layout.fragment_detail_followers) {
    private var _binding : FragmentDetailFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var username: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentDetailFollowersBinding.bind(view)
        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.apply {
            rvUserFollowers.setHasFixedSize(true)
            rvUserFollowers.layoutManager = LinearLayoutManager(activity)
            rvUserFollowers.adapter = userAdapter
        }

            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                FollowersViewModel::class.java)
            viewModel.setListFollowers(username)
            viewModel.getListFollowers().observe(viewLifecycleOwner,{
                if (it!=null){
                    userAdapter.setList(it)
                    binding.tvNotFound.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
                }
            })
        viewModel.isLoading.observe(viewLifecycleOwner,{
            showLoading(it)
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
            tvNotFound.visibility = View.GONE
        }

    }
}