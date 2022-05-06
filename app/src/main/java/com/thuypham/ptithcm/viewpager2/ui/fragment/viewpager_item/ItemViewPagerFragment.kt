package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager_item

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentItemViewpagerBinding
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter
import java.lang.String
import java.util.*

class ItemViewPagerFragment : BaseFragment<FragmentItemViewpagerBinding>(R.layout.fragment_item_viewpager) {
    private var position = 0
    override fun setupView() {
        val content = arguments?.getSerializable(Viewpager2Adapter.KEY) as Item?

        binding.tvContent.text = content?.message
        val hexColor = String.format("#%06X", 0xFFFFFF and (content?.color?: 0))
        val color: Int = Color.parseColor(hexColor)
        binding.container.setBackgroundColor(color)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = arguments?.getInt("pos")?:0
        Log.d(TAG, "onCreate $position")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.d(TAG, "onCreateView $position" )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated $position")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause $position")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart $position")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop $position")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy $position")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView $position")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume $position")
    }
}