package com.parser.moviedb.presentation.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.parser.moviedb.presentation.R
import com.parser.moviedb.presentation.databinding.ChipViewBinding

data class MovieFilter(val title: String, val type: MovieFilterType, val filterValue: String? = null)

data class MovieFilterWrapper(val filter: MovieFilter, var selected: Boolean)

enum class MovieFilterType {
    LANGUAGE, YEAR_OF_RELEASE
}

class MovieFilterAdapter(private val context: Context, private val callback: (filter: MovieFilter) -> Unit) : ListAdapter<MovieFilterWrapper, MovieFilterAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieFilterWrapper>() {
            override fun areItemsTheSame(
                oldItem: MovieFilterWrapper,
                newItem: MovieFilterWrapper
            ): Boolean {
                return oldItem.filter.title == newItem.filter.title
            }

            override fun areContentsTheSame(
                oldItem: MovieFilterWrapper,
                newItem: MovieFilterWrapper
            ): Boolean {
                return oldItem.filter.title == newItem.filter.title && oldItem.filter.filterValue == newItem.filter.filterValue &&
                    oldItem.filter.type == newItem.filter.type
            }
        }
    }

    inner class ViewHolder(private val binding: ChipViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieFilterWrapper, position: Int) {

            if (item.selected) {
                binding.bg.background = ContextCompat.getDrawable(context, R.drawable.white_rounded_shape)
                binding.tvTitle.setTextColor(context.getColor(android.R.color.black))
            } else {
                binding.bg.background = ContextCompat.getDrawable(context, R.drawable.transparent_rounded_shape)
                binding.tvTitle.setTextColor(context.getColor(android.R.color.white))
            }

            binding.title = item.filter.title

            binding.root.setOnClickListener {
                item.selected = !item.selected
                currentList[position].selected = item.selected
                notifyItemChanged(position)
                val returnFilter = if (item.selected) item.filter else item.filter.copy(filterValue = null)
                callback.invoke(returnFilter)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChipViewBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
}