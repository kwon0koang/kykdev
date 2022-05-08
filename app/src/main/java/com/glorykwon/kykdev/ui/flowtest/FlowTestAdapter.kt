package com.glorykwon.kykdev.ui.flowtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glorykwon.kykdev.databinding.ItemFlowTestBinding
import com.glorykwon.kykdev.api.RetrofitTestDto

class FlowTestAdapter(val itemClickListener: ((RetrofitTestDto) -> Unit)? = null) : PagingDataAdapter<RetrofitTestDto, FlowTestAdapter.FlowTestViewHolder>(diffCallback) {

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
        val binding = ItemFlowTestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val viewHolder = FlowTestViewHolder(binding)
        viewHolder.binding.layoutItemSelectDefaultList.setOnClickListener {
            val item = getItem(viewHolder.absoluteAdapterPosition)
            if (item != null) {
                itemClickListener?.invoke(item)
            }
        }

        return FlowTestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlowTestViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class FlowTestViewHolder(val binding: ItemFlowTestBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RetrofitTestDto) {
            binding.txtFlowTest.text = data.title
        }
    }
}