package com.wcp.tmdbcleanarchitecture.internal.di.components

import com.wcp.tmdbcleanarchitecture.MovieApplication
import com.wcp.tmdbcleanarchitecture.internal.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class,
        FragmentModule::class,
        DataSourceModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MovieApplication): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: MovieApplication)
}



