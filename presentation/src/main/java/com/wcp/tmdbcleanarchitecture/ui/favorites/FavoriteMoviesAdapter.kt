package com.wcp.tmdbcleanarchitecture.ui.favorites

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.tmdbcleanarchitecture.R
import com.wcp.tmdbcleanarchitecture.internal.extension.inflate
import com.wcp.tmdbcleanarchitecture.internal.extension.loadFromUrl
import kotlinx.android.synthetic.main.movie_card.view.*

class FavoriteMoviesAdapter(
    private val listener: OnClickedListener
) : ListAdapter<FavoriteMovieDataModel, PopularViewHolder>(
    object : DiffUtil.ItemCallback<FavoriteMovieDataModel>() {
        override fun areItemsTheSame(
            oldItem: FavoriteMovieDataModel,
            newItem: FavoriteMovieDataModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: FavoriteMovieDataModel,
            newItem: FavoriteMovieDataModel
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
        fun onPosterClicked(favoriteMovieDataModel: FavoriteMovieDataModel)
        fun onFavoriteClicked(favoriteMovieDataModel: FavoriteMovieDataModel)
    }
}

class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(
        favoriteMovieDataModel: FavoriteMovieDataModel,
        listener: FavoriteMoviesAdapter.OnClickedListener
    ) {

        val url = "https://image.tmdb.org/t/p/w500/" + favoriteMovieDataModel.poster_path
        itemView.iv_poster.loadFromUrl(url)
        itemView.tv_title.text = favoriteMovieDataModel.title
        itemView.tv_date.text = favoriteMovieDataModel.release_date ?: "0000-00-00"

        itemView.setOnClickListener {
            listener.onPosterClicked(favoriteMovieDataModel)
        }

        itemView.iv_favorite_movie.setOnClickListener {
            listener.onFavoriteClicked(favoriteMovieDataModel)
        }
    }
}