package com.wcp.tmdbcleanarchitecture.internal.di.modules

import androidx.lifecycle.ViewModel
import com.wcp.tmdbcleanarchitecture.ui.favorites.FavoritesViewModel
import com.wcp.tmdbcleanarchitecture.ui.movieDetails.MovieDetailsViewModel
import com.wcp.tmdbcleanarchitecture.ui.popular.PopularViewModel
import com.wcp.tmdbcleanarchitecture.ui.upcoming.UpcomingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UpcomingViewModel::class)
    abstract fun bindUpcomingViewModel(upcomingViewModel: UpcomingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(popularViewModel: PopularViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel

  /*  @Binds
    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    abstract fun bindFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel*/

}