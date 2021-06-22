package com.wcp.data.mapper

import com.wcp.data.dataSource.localDataSource.favorites.FavoriteMovieData
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.data.utils.getLocalTimeFromUnix

class FavoriteMoviesDataMapper {

    private fun transform(favoriteMovieData: FavoriteMovieData): FavoriteMovieDataModel {
        return FavoriteMovieDataModel(
            id = favoriteMovieData.id,
            poster_path = favoriteMovieData.poster_path,
            release_date = favoriteMovieData.release_date,
            title = favoriteMovieData.title,
            vote_average = favoriteMovieData.vote_average,
            vote_count = favoriteMovieData.vote_count,

            )
    }

    fun transform(list: List<FavoriteMovieData>): List<FavoriteMovieDataModel> {

        return list.map {
            transform(it)
        }
    }
}

