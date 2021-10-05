//package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail
//
//import android.content.Context
//import androidx.annotation.StringRes
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.FragmentManager
//import androidx.fragment.app.FragmentPagerAdapter
//import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
//
//class SectionPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//    @StringRes
//    private val TAB_TITLES = intArrayOf(R.string.followers,R.string.following)
//
//    override fun getCount(): Int = 2
//
//    override fun getItem(position: Int): Fragment {
//        var fragment: Fragment? = null
//        when(position){
//            0 -> FollowersFragment()
//            1 -> FollowingFragment()
//        }
//        return fragment as Fragment
//    }
//
//    override fun getPageTitle(position: Int): CharSequence? {
//        return context.resources.getString(TAB_TITLES[position])
//    }
//
//}