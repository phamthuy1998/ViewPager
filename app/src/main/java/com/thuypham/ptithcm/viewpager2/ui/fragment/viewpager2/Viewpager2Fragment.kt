package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.viewpager2.widget.ViewPager2
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentViewpager2Binding
import com.thuypham.ptithcm.viewpager2.extension.goBack
import com.thuypham.ptithcm.viewpager2.extension.navigateTo
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.DETAIL_KEY
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.ITEM_CHANGE

class Viewpager2Fragment : BaseFragment<FragmentViewpager2Binding>(R.layout.fragment_viewpager_2) {
    private  var viewpager2Adapter: Viewpager2Adapter? = null

    private var currentPagerPosition = 0

    private val listPagerItems = arrayListOf(

        Item("This is first page", "#FF000000"),
        Item("This is second page", "#FFFB0000"),
        Item("This is third page","#FFFFEB3B"),
        Item("This is four page", "#FF404040"),
        Item("This is five page", "#FF4CAF50"),
        Item("This is six page", "#FFFF9800"),
        Item("This is seven page", "#FFBB86FC"),
        Item("This is eight page", "#FF6200EE"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPagerPosition = arguments?.getInt(ViewpagerFragment.CURRENT_POS,0) ?:0
        setFragmentResultListener(ITEM_CHANGE) { requestKey, bundle ->
            val newContent = bundle.getSerializable(DETAIL_KEY) as Item?
            if (newContent != null) {
                listPagerItems[currentPagerPosition] = newContent
                runOnUiThread {
                    viewpager2Adapter?.notifyItemChanged(currentPagerPosition)
//                    viewpager2Adapter.notifyDataSetChanged()

                }
            }
        }

    }

    override fun setupView() {
        setupToolbar()
        setupViewPager()
        setupEvent()
    }


    private fun setupEvent() {
        binding.apply {
            btnEdit.setOnSingleClickListener {
                navigateTo(R.id.detail, bundleOf(Viewpager2Adapter.KEY to listPagerItems[currentPagerPosition]))
            }
        }
    }

    private fun setupViewPager() {
        binding.pager.apply {
            viewpager2Adapter = Viewpager2Adapter(requireActivity())
            adapter = viewpager2Adapter

            viewpager2Adapter?.submitList(listPagerItems)
            setCurrentItem(currentPagerPosition, true)
//            setPageTransformer(ZoomOutPageTransformer())
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }

                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    currentPagerPosition = position
                }
            })
        }

    }


    private fun setupToolbar() {
        setToolbarTitle(R.string.viewpager_2)
        setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFragmentResultListener(ITEM_CHANGE)
    }

}