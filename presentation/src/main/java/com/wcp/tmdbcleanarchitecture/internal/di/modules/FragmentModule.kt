package com.wcp.tmdbcleanarchitecture.internal.di.modules

import com.wcp.tmdbcleanarchitecture.ui.favorites.FavoritesFragment
import com.wcp.tmdbcleanarchitecture.ui.movieDetails.MovieDetailsFragment
import com.wcp.tmdbcleanarchitecture.ui.popular.PopularFragment
import com.wcp.tmdbcleanarchitecture.ui.upcoming.UpcomingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector abstract fun bindUpcomingFragment(): UpcomingFragment
    @ContributesAndroidInjector abstract fun bindPopularFragment(): PopularFragment
    @ContributesAndroidInjector abstract fun bindFavoritesFragment(): FavoritesFragment
    @ContributesAndroidInjector abstract fun bindMovieDetailsFragment(): MovieDetailsFragment

}