package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager_item

import android.graphics.Color
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentItemViewpagerBinding
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter
import java.util.*

class ItemViewPagerFragment : BaseFragment<FragmentItemViewpagerBinding>(R.layout.fragment_item_viewpager) {
    override fun setupView() {
        val content = arguments?.getString(Viewpager2Adapter.KEY, "Default value")
        binding.tvContent.text = content
        val rnd = Random()
        val color: Int = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        binding.container.setBackgroundColor(color)
    }
}