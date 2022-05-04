package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager_item

import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentItemViewpagerBinding
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter
import java.util.*

class ItemViewPagerFragment : BaseFragment<FragmentItemViewpagerBinding>(R.layout.fragment_item_viewpager) {
    override fun setupView() {
        val content = arguments?.getSerializable(Viewpager2Adapter.KEY) as Item?
        binding.tvContent.text = content?.message
        val rnd = Random()
        val color: Int = resources.getColor(content?.color ?: R.color.white)
        binding.container.setBackgroundColor(color)
    }
}