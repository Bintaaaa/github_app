package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail.FollowersFragment
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail.FollowingFragment


class SectionPagerAdapter(activity: AppCompatActivity, data: Bundle) : FragmentStateAdapter(activity) {
    private var fragmentBundle: Bundle = data

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
       when(position){
           0 -> fragment = FollowersFragment()
           1 -> fragment = FollowingFragment()
       }
        fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }


}