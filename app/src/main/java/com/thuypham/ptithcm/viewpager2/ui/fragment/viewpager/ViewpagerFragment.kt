package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.viewpager.widget.ViewPager
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentViewpagerBinding
import com.thuypham.ptithcm.viewpager2.extension.goBack
import com.thuypham.ptithcm.viewpager2.extension.navigateTo
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter

class ViewpagerFragment : BaseFragment<FragmentViewpagerBinding>(R.layout.fragment_viewpager) {
    companion object {
        const val ITEM_CHANGE = "REQUEST_KEY"
        const val DETAIL_KEY = "DETAIL_KEY"
        const val CURRENT_POS = "CURRENT_POS"
    }

    private var viewpagerApdapter: ViewpagerAdapter? = null

    private var currentPagerPosition = 0

    private val listPagerItems = arrayListOf(

        Item("This is first page", R.color.black),
        Item("This is second page", R.color.red),
        Item("This is third page", R.color.yellow),
        Item("This is four page", R.color.green),
        Item("This is five page", R.color.gray),
        Item("This is six page", R.color.color_orange_FF9800),
        Item("This is seven page", R.color.teal_200),
        Item("This is eight page", R.color.teal_700),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPagerPosition = arguments?.getInt(CURRENT_POS, 0) ?: 0
        setFragmentResultListener(ITEM_CHANGE) { requestKey, bundle ->
            val newContent = bundle.getSerializable(DETAIL_KEY) as Item?
            if (newContent != null) {
                listPagerItems[currentPagerPosition] = newContent
                runOnUiThread {
                    viewpagerApdapter?.notifyDataSetChanged()
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
            viewpagerApdapter = ViewpagerAdapter(childFragmentManager)
            adapter = viewpagerApdapter

            viewpagerApdapter?.submitList(listPagerItems)
            setCurrentItem(currentPagerPosition, true)
            addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

                }

                override fun onPageSelected(position: Int) {
                    currentPagerPosition = position
                }

                override fun onPageScrollStateChanged(state: Int) {

                }

            })

        }
    }


    private fun setupToolbar() {
        setToolbarTitle(R.string.viewpager)
        setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFragmentResultListener(ITEM_CHANGE)
    }
}