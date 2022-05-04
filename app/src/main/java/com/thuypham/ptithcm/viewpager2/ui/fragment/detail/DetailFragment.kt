package com.thuypham.ptithcm.viewpager2.ui.fragment.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentDetailBinding
import com.thuypham.ptithcm.viewpager2.extension.goBack
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.DETAIL_KEY
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.ITEM_CHANGE
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private var item: Item? = null
    override fun setupView() {
        setupToolbar()
        setData()
        setupEvent()
    }

    private fun setupEvent() {
        binding.apply {
            btnSave.setOnSingleClickListener {
                item?.message = edtContent.text.toString()
                setFragmentResult(ITEM_CHANGE, bundleOf(DETAIL_KEY to item))
                goBack()
            }
        }
    }

    private fun setupToolbar() {
        setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
    }

    private fun setData() {
        item = arguments?.getSerializable(Viewpager2Adapter.KEY) as Item?
        binding.edtContent.setText(item?.message)
    }

}