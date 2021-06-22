package com.wcp.tmdbcleanarchitecture.ui.upcoming

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wcp.tmdbcleanarchitecture.R
import com.wcp.tmdbcleanarchitecture.internal.extension.inflate
import com.wcp.tmdbcleanarchitecture.internal.extension.loadFromUrl
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel
import kotlinx.android.synthetic.main.movie_card.view.*

class UpcomingMoviesAdapter(
    private val listener: OnClickedListener
) : ListAdapter<UpComingUIModel, UpcomingViewHolder>(
    object : DiffUtil.ItemCallback<UpComingUIModel>() {
        override fun areItemsTheSame(
            oldItem: UpComingUIModel,
            newItem: UpComingUIModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UpComingUIModel,
            newItem: UpComingUIModel
        ): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        return UpcomingViewHolder(parent.inflate(R.layout.movie_card))
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    interface OnClickedListener {
        fun onPosterClicked(upComingUIModel: UpComingUIModel)
        fun onFavoriteClicked(upComingUIModel: UpComingUIModel)
    }
}

class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(upComingUIModel: UpComingUIModel, listener: UpcomingMoviesAdapter.OnClickedListener) {

        val url =  "https://image.tmdb.org/t/p/w500/"+ upComingUIModel.poster_path
        itemView.iv_poster.loadFromUrl(url)
        itemView.tv_title.text = upComingUIModel.title
        itemView.tv_date.text = upComingUIModel.release_date

        itemView.setOnClickListener {
            listener.onPosterClicked(upComingUIModel)
        }
        itemView.iv_favorite_movie.setOnClickListener {
            listener.onFavoriteClicked(upComingUIModel)
        }
    }
}