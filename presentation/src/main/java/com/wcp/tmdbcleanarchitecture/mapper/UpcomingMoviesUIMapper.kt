package com.wcp.tmdbcleanarchitecture.mapper

import com.wcp.domain.models.UpComingDataModel
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel
import com.wcp.data.utils.getLocalTimeFromUnix

class UpcomingMoviesUIMapper {

    private fun transform(dataModel: UpComingDataModel): UpComingUIModel {
        return UpComingUIModel(
            id= dataModel.id,
            poster_path = dataModel.poster_path,
            release_date = getLocalTimeFromUnix(dataModel.release_date?:"0000-00-00"),
            title = dataModel.title,
            vote_average = dataModel.vote_average,
            vote_count = dataModel.vote_count
        )
    }

    fun transform(list: List<UpComingDataModel>): List<UpComingUIModel> {
        return list.map {
           transform(it)
        }
    }

}