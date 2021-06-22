package com.wcp.tmdbcleanarchitecture.ui.popular

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wcp.tmdbcleanarchitecture.R
import com.wcp.tmdbcleanarchitecture.internal.extension.inflate
import com.wcp.tmdbcleanarchitecture.internal.extension.loadFromUrl
import com.wcp.tmdbcleanarchitecture.models.PopularUIModel
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel
import kotlinx.android.synthetic.main.movie_card.view.*

class PopularMoviesAdapter(
    private val listener: OnClickedListener
) : ListAdapter<PopularUIModel, PopularViewHolder>(
    object : DiffUtil.ItemCallback<PopularUIModel>() {
        override fun areItemsTheSame(oldItem: PopularUIModel, newItem: PopularUIModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PopularUIModel,
            newItem: PopularUIModel
        ): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(parent.inflate(R.layout.movie_card))
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface OnClickedListener {
        fun onPosterClicked(popularUIModel: PopularUIModel)
        fun onFavoriteClicked(popularUIModel: PopularUIModel)
    }
}

class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(popularUIModel: PopularUIModel, listener: PopularMoviesAdapter.OnClickedListener) {

        val url = "https://image.tmdb.org/t/p/w500/" + popularUIModel.poster_path
        itemView.iv_poster.loadFromUrl(url)
        itemView.tv_title.text = popularUIModel.title
        itemView.tv_date.text = popularUIModel.release_date

        itemView.setOnClickListener {
            listener.onPosterClicked(popularUIModel)
        }

        itemView.iv_favorite_movie.setOnClickListener {
            listener.onFavoriteClicked(popularUIModel)
        }
    }
}