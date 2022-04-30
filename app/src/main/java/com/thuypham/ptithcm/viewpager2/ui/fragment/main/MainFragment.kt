package com.thuypham.ptithcm.viewpager2.ui.fragment.main

import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentMainBinding
import com.thuypham.ptithcm.viewpager2.extension.navigateTo
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun setupView() {
        setToolbarTitle(R.string.main)
        binding.apply {
            btnViewpager.setOnSingleClickListener {
                navigateTo(R.id.viewpager)
            }
            btnViewpager2.setOnSingleClickListener {
                navigateTo(R.id.viewpager2)
            }
        }
    }
}