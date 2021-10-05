package com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.R
import com.bijantyum.submissionbelajarfundamentalaplikasiandroid2.databinding.FragmentDetailFollowBinding

class FollowingFragment: Fragment(R.layout.fragment_detail_follow) {
    private var _binding : FragmentDetailFollowBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailFollowBinding.bind(view)
    }

}