package com.wcp.tmdbcleanarchitecture.internal.di.modules

import com.wcp.tmdbcleanarchitecture.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}