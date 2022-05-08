package com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.thuypham.ptithcm.viewpager2.model.Item
import com.thuypham.ptithcm.viewpager2.ui.fragment.viewpager_item.ItemViewPagerFragment

class ViewpagerAdapter(fa: FragmentManager) : FragmentStatePagerAdapter(fa,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT ) {
    companion object {
        const val KEY = "KEY"
    }

    private var listItems: ArrayList<Item> = arrayListOf()

    fun submitList(listItems: ArrayList<Item>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return listItems.size
    }

    override fun getItem(position: Int): Fragment {
        val fragment = ItemViewPagerFragment()
        val bundle = Bundle().apply {
            putSerializable(KEY, listItems[position])
            putInt("pos", position)
        }
        fragment.arguments = bundle
        return fragment
    }

    override fun getItemPosition(ob: Any): Int {
        return POSITION_NONE
    }

    override fun saveState(): Parcelable? {
        return null
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {

    }
}