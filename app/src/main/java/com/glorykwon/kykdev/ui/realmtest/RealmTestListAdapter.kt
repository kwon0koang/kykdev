package com.glorykwon.kykdev.ui.realmtest

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.glorykwon.kykdev.database.realm.dao.TodoRealmObject
import com.glorykwon.kykdev.databinding.ItemSimpleBinding

class RealmTestListAdapter(val context: Context, val viewModel: RealmTestViewModel, val itemClickListener: ((TodoRealmObject) -> Unit)? = null)
    : ListAdapter<TodoRealmObject, RealmTestListAdapter.RealmTestViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<TodoRealmObject>() {
            override fun areItemsTheSame(oldItem: TodoRealmObject, newItem: TodoRealmObject): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: TodoRealmObject, newItem: TodoRealmObject): Boolean {
                return oldItem.equals(newItem)//oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealmTestViewHolder {
        val binding = ItemSimpleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RealmTestViewHolder(binding).apply {
            binding.btnCancel.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                if (item != null) {
                    itemClickListener?.invoke(item)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RealmTestViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class RealmTestViewHolder(val binding: ItemSimpleBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TodoRealmObject) {
            binding.txtTest.text = data.content
        }
    }
}