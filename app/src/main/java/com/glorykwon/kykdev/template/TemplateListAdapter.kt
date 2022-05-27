package com.glorykwon.kykdev.template

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glorykwon.kykdev.common.api.RetrofitTestDto
import com.glorykwon.kykdev.databinding.ItemSimpleBinding

class TemplateListAdapter(val context: Context, val viewModel: TemplateViewModel, val itemClickListener: ((RetrofitTestDto) -> Unit)? = null)
    : ListAdapter<RetrofitTestDto, TemplateListAdapter.TestViewHolder>(diffCallback) {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = ItemSimpleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TestViewHolder(binding).apply {
            binding.btnCancel.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                if (item != null) {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class TestViewHolder(val binding: ItemSimpleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RetrofitTestDto) {
            binding.txtTest.text = data.title
        }
    }
}