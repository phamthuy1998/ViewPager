package com.thuypham.ptithcm.viewpager2.ui.fragment.recyclerview

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.thuypham.ptithcm.viewpager2.databinding.FragmentItemViewpagerBinding
import com.thuypham.ptithcm.viewpager2.model.Item

class ItemAdapter(
    private val onItemSelected: ((item: Item) -> Unit)? = null,
) : ListAdapter<Item, RecyclerView.ViewHolder>(DiffCallback()) {

    private var canSelected: Boolean = true

    fun setCanSelected(canSelect: Boolean) {
        canSelected = canSelect
    }

    class MediaItemViewHolder(
        private val binding: FragmentItemViewpagerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                binding.tvContent.text = item.message
                val color: Int = Color.parseColor(item.color ?:"#FFFFFFFF")
                binding.container.setBackgroundColor(color)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MediaItemViewHolder {
        Log.d("thuyy","onCreateViewHolder" )
        val binding = FragmentItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MediaItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MediaItemViewHolder).bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item) =
            oldItem.color == newItem.color && oldItem.message == newItem.message

        override fun areContentsTheSame(oldItem: Item, newItem: Item) =
            oldItem == newItem
    }
}