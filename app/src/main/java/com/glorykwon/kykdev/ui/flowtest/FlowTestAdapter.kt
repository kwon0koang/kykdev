package com.glorykwon.kykdev.ui.flowtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.glorykwon.kykdev.databinding.ItemFlowTestBinding
import com.glorykwon.kykdev.dto.TestDto

class FlowTestAdapter(val itemClickListener: (TestDto) -> Unit) : PagingDataAdapter<TestDto, FlowTestAdapter.FlowTestViewHolder>(diffCallback) {

//    var mItemClickListener: OnItemClickListener? = null
//    interface OnItemClickListener {
//        fun onItemClick(listData: TestDto)
//    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TestDto>() {
            override fun areItemsTheSame(oldItem: TestDto, newItem: TestDto): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TestDto, newItem: TestDto): Boolean {
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
        return FlowTestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlowTestViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item)
            holder.itemView.setOnClickListener{
//                itemClickListener?.onItemClick(item)
                itemClickListener.invoke(item)
            }
        }
    }

    class FlowTestViewHolder(val binding: ItemFlowTestBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TestDto) {
            binding.apply {
                txtFlowTest.text = data.value01
            }
        }
    }
}