package com.thuypham.ptithcm.viewpager2.ui.fragment.main

import androidx.core.os.bundleOf
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentMainBinding
import com.thuypham.ptithcm.viewpager2.extension.navigateTo
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun setupView() {
        setToolbarTitle(R.string.main)
        binding.apply {
            btnViewpager.setOnSingleClickListener {
                val position = if(edtPage.text.isNullOrBlank())0 else edtPage.text.toString().toInt()
                navigateTo(R.id.viewpager, bundleOf(ViewpagerFragment.CURRENT_POS to position))
            }
            btnViewpager2.setOnSingleClickListener {
                val position = if(edtPage.text.isNullOrBlank())0 else edtPage.text.toString().toInt()
                navigateTo(R.id.viewpager2, bundleOf(ViewpagerFragment.CURRENT_POS to position))
            }
        }
    }
}