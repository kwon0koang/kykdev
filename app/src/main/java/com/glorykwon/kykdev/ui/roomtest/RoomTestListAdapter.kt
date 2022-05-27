package com.glorykwon.kykdev.ui.roomtest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glorykwon.kykdev.common.database.room.entity.TodoRoomEntity
import com.glorykwon.kykdev.databinding.ItemSimpleBinding

class RoomTestListAdapter(val context: Context, val viewModel: RoomTestViewModel, val itemClickListener: ((TodoRoomEntity) -> Unit)? = null)
    : ListAdapter<TodoRoomEntity, RoomTestListAdapter.RoomTestViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TodoRoomEntity>() {
            override fun areItemsTheSame(oldItem: TodoRoomEntity, newItem: TodoRoomEntity): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TodoRoomEntity, newItem: TodoRoomEntity): Boolean {
                return oldItem.equals(newItem)//oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomTestViewHolder {
        val binding = ItemSimpleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RoomTestViewHolder(binding).apply {
            binding.btnCancel.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                if (item != null) {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RoomTestViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class RoomTestViewHolder(val binding: ItemSimpleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TodoRoomEntity) {
            binding.txtTest.text = data.content
        }
    }
}