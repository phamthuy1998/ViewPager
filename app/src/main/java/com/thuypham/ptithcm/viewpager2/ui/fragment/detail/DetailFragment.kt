package com.thuypham.ptithcm.viewpager2.ui.fragment.detail

import android.graphics.Color
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.skydoves.colorpickerview.ColorEnvelope
import com.skydoves.colorpickerview.ColorPickerDialog
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentDetailBinding
import com.thuypham.ptithcm.viewpager2.extension.goBack
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.DETAIL_KEY
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment.Companion.ITEM_CHANGE
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter
import java.lang.String


class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private var item: Item? = null
    private var currentColor: ColorEnvelope? = null
    override fun setupView() {
        setupToolbar()
        setData()
        setupEvent()
    }

    private fun setupEvent() {
        binding.apply {
            btnSave.setOnSingleClickListener {
                item?.message = edtContent.text.toString()
                if (currentColor != null) item?.color = Color.parseColor("#${currentColor!!.hexCode}")
                setFragmentResult(ITEM_CHANGE, bundleOf(DETAIL_KEY to item))
                goBack()
            }

            btnSelectColor.setOnSingleClickListener {
                ColorPickerDialog.Builder(requireContext())
                    .setTitle("ColorPicker Dialog")
                    .setPreferenceName("MyColorPickerDialog")
                    .setPositiveButton("ok", ColorEnvelopeListener { envelope, fromUser ->
                        currentColor = envelope

                        it.setBackgroundColor(Color.parseColor("#${envelope.hexCode}"))
                    })
                    .setNegativeButton("cancel") { dialogInterface, i -> dialogInterface.dismiss() }
                    .attachAlphaSlideBar(true) // the default value is true.
                    .attachBrightnessSlideBar(true) // the default value is true.
                    .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                    .show()
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
        item?.color?.let {
            val hexColor = String.format("#%06X", 0xFFFFFF and it)
            binding.viewCurrentColor.setBackgroundColor(Color.parseColor(hexColor))
        }
    }

}