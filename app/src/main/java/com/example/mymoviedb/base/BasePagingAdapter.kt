package com.example.mymoviedb.base

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BasePagingAdapter<T: Any, VB: ViewBinding>(
    private val layoutFactory: (LayoutInflater, ViewGroup?, Boolean) -> VB,
    private val onItemBind: (T, VB, View) -> Unit
) : PagingDataAdapter<T, BasePagingAdapter.BasePagingViewHolder<T, VB>>(BasePagingItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePagingViewHolder<T, VB> {
        val binding = layoutFactory(LayoutInflater.from(parent.context), parent, false)
        val view = binding.root
        return BasePagingViewHolder(view, binding, onItemBind)
    }

    override fun onBindViewHolder(holder: BasePagingViewHolder<T, VB>, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    class BasePagingViewHolder<T: Any, VB: ViewBinding>(
        view: View,
        private val binding: VB,
        private val onItemBind: (T, VB, View) -> Unit
    ) : RecyclerView.ViewHolder(view){
        fun bind(item: T) {
            onItemBind(item, binding, itemView)
        }

    }

    class BasePagingItemCallback<T: Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

    }
}