package com.wcp.tmdbcleanarchitecture.mapper

import com.wcp.domain.models.PopularDataModel
import com.wcp.tmdbcleanarchitecture.models.PopularUIModel
import com.wcp.data.utils.getLocalTimeFromUnix

class PopularMoviesUIMapper {

    private fun transform(dataModel: PopularDataModel): PopularUIModel {
        return PopularUIModel(
            id= dataModel.id,
            poster_path = dataModel.poster_path,
            release_date = getLocalTimeFromUnix(dataModel.release_date?:"0000-00-00"),
            title = dataModel.title,
            vote_average = dataModel.vote_average,
            vote_count = dataModel.vote_count,
        )
    }

    fun transform(list: List<PopularDataModel>): List<PopularUIModel> {
        return list.map {
           transform(it)
        }
    }

}