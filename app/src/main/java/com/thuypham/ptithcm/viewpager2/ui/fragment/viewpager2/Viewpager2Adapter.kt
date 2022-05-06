package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager_item.ItemViewPagerFragment

class Viewpager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    companion object {
        const val KEY = "KEY"
    }

    private var listItems: ArrayList<Item> = arrayListOf()

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = ItemViewPagerFragment()
        val bundle = Bundle().apply {
            putSerializable(KEY, listItems[position])
            putInt("pos", position)
        }
        fragment.arguments = bundle
        return fragment
    }

    fun submitList(listItems: ArrayList<Item>) {
        this.listItems = listItems
        notifyItemRangeChanged(0, listItems.size)
    }

    override fun getItemId(position: Int): Long {
        return listItems[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        // false if item is changed
        return listItems.find { it.hashCode().toLong() == itemId } != null
    }
}