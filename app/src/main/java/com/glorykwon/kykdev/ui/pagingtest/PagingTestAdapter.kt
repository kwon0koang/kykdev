package com.glorykwon.kykdev.ui.pagingtest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import com.glorykwon.kykdev.databinding.ItemSimpleBinding

class PagingTestAdapter(val context: Context, val viewModel: PagingTestViewModel, val itemClickListener: ((RetrofitTestDto) -> Unit)? = null)
    : PagingDataAdapter<RetrofitTestDto, PagingTestAdapter.FlowTestViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RetrofitTestDto>() {
            override fun areItemsTheSame(oldItem: RetrofitTestDto, newItem: RetrofitTestDto): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: RetrofitTestDto, newItem: RetrofitTestDto): Boolean {
                return oldItem.equals(newItem)//oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowTestViewHolder {
        val binding = ItemSimpleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return FlowTestViewHolder(binding).apply {
            binding.layoutItemSelectDefaultList.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                if (item != null) {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: FlowTestViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class FlowTestViewHolder(val binding: ItemSimpleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RetrofitTestDto) {
            binding.txtTest.text = data.title
            binding.btnCancel.isVisible = false
        }
    }
}