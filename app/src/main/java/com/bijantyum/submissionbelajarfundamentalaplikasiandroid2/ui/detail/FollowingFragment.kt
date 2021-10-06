package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter.UserAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.FragmentDetailFollowingBinding

class FollowingFragment: Fragment(R.layout.fragment_detail_following) {
    private var _binding : FragmentDetailFollowingBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowingViewModel
    private lateinit var userAdapter: UserAdapter
    private lateinit var username: String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        username = args?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = FragmentDetailFollowingBinding.bind(view)
        userAdapter = UserAdapter()
        userAdapter.notifyDataSetChanged()

        binding.apply {
            rvUserFollowing.setHasFixedSize(true)
            rvUserFollowing.layoutManager = LinearLayoutManager(activity)
            rvUserFollowing.adapter = userAdapter
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner,{
            if (it!=null){
                userAdapter.setList(it)
                viewModel.isLoading.observe(viewLifecycleOwner,{
                    showLoading(it)
                })
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressbar.visibility = if (isLoading) View.VISIBLE else View.GONE
   }
}