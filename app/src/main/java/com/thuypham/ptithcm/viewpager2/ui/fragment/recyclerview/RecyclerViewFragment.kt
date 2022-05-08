package com.thuypham.ptithcm.viewpager2.ui.fragment.recyclerview

import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.thuypham.ptithcm.viewpager2.R
import com.thuypham.ptithcm.viewpager2.base.BaseFragment
import com.thuypham.ptithcm.viewpager2.databinding.FragmentRecyclerviewBinding
import com.thuypham.ptithcm.viewpager2.extension.goBack
import com.thuypham.ptithcm.viewpager2.extension.navigateTo
import com.thuypham.ptithcm.viewpager2.extension.setOnSingleClickListener
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager.ViewpagerFragment
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2.Viewpager2Adapter


class RecyclerViewFragment : BaseFragment<FragmentRecyclerviewBinding>(R.layout.fragment_recyclerview) {
    private val itemAdapter: ItemAdapter by lazy {
        ItemAdapter()
    }

    private var currentPagerPosition = 0

    private val listPagerItems = arrayListOf(

        Item("This is first page", "#FF000000"),
        Item("This is second page", "#FFFB0000"),
        Item("This is third page", "#FFFFEB3B"),
        Item("This is four page", "#FF404040"),
        Item("This is five page", "#FF4CAF50"),
        Item("This is six page", "#FFFF9800"),
        Item("This is seven page", "#FFBB86FC"),
        Item("This is eight page", "#FF6200EE"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPagerPosition = arguments?.getInt(ViewpagerFragment.CURRENT_POS, 0) ?: 0
        setFragmentResultListener(ViewpagerFragment.ITEM_CHANGE) { requestKey, bundle ->
            val newContent = bundle.getSerializable(ViewpagerFragment.DETAIL_KEY) as Item?
            if (newContent != null) {
                listPagerItems[currentPagerPosition] = newContent
                runOnUiThread {
                    itemAdapter.notifyItemChanged(currentPagerPosition)
                }
            }
        }

    }

    override fun setupView() {
        setupToolbar()
        setupRecyclerView()
        setupEvent()

    }

    private fun setupEvent() {
        binding.apply {
            btnEdit.setOnSingleClickListener {
                navigateTo(R.id.detail, bundleOf(Viewpager2Adapter.KEY to listPagerItems[currentPagerPosition]))
            }
        }
    }

    private fun setupRecyclerView() {
        Log.d("thuyy","setupRecyclerView ${listPagerItems.size}" )
        binding.rvItems.apply {
            val snapHelper: SnapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
            adapter = itemAdapter
            itemAdapter.submitList(listPagerItems)
            itemAdapter.notifyDataSetChanged()
            Log.d("thuyy","setupRecyclerView ${listPagerItems.size}" )
            scrollToPosition(currentPagerPosition)
        }

    }


    private fun setupToolbar() {
        setToolbarTitle(R.string.recycler_view)
        setLeftBtn(R.drawable.ic_back) {
            goBack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFragmentResultListener(ViewpagerFragment.ITEM_CHANGE)
    }
}