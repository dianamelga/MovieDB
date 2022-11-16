package com.parser.moviedb.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.parser.moviedb.domain.entities.MediaItem
import com.parser.moviedb.presentation.databinding.MovieItemBinding
import com.parser.moviedb.presentation.databinding.MovieItemRecommendedBinding

data class MediaItemWrapper(val item: MediaItem, var viewType: MovieItemAdapter.ViewType)

class MovieItemAdapter(private val onClick:(MediaItem) -> Unit) : ListAdapter<MediaItemWrapper, MovieItemAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MediaItemWrapper>() {
            override fun areItemsTheSame(
                oldItem: MediaItemWrapper,
                newItem: MediaItemWrapper
            ): Boolean {
                return oldItem.item.id == newItem.item.id
            }

            override fun areContentsTheSame(
                oldItem: MediaItemWrapper,
                newItem: MediaItemWrapper
            ): Boolean {
                return oldItem.item.id == newItem.item.id && oldItem.item.title == newItem.item.title
            }
        }
    }

    inner class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MediaItemWrapper) {
            if (binding is MovieItemBinding) {
                binding.imageUrl = movie.item.posterUrl
            }
            if (binding is MovieItemRecommendedBinding) {
                binding.imageUrl = movie.item.posterUrl
            }

            binding.root.setOnClickListener {
                onClick.invoke(movie.item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = when (viewType) {
            ViewType.MOVIE_ITEM_RECOMMENDED_CARD.value -> MovieItemRecommendedBinding.inflate(inflater, parent, false)
            ViewType.MOVIE_ITEM_CARD.value -> MovieItemBinding.inflate(inflater, parent, false)
            else -> MovieItemBinding.inflate(inflater, parent, false)
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.value
    }

    enum class ViewType(val value: Int) {
        MOVIE_ITEM_CARD(0),
        MOVIE_ITEM_RECOMMENDED_CARD(1)
    }
}
