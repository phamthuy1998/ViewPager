package com.thuypham.ptithcm.viewpager2.ui.fragment.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentDetailBinding
import com.thuypham.ptithcm.viewpager2.extension.goBack
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.DETAIL_KEY
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.ITEM_CHANGE
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    override fun setupView() {
        setupToolbar()
        setData()
        setupEvent()
    }

    private fun setupEvent() {
        binding.apply {
            btnSave.setOnSingleClickListener {
                setFragmentResult(ITEM_CHANGE, bundleOf(DETAIL_KEY to edtContent.text.toString()))
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
        val content = arguments?.getString(Viewpager2Adapter.KEY, "Default value")
        binding.edtContent.setText(content)
    }

}