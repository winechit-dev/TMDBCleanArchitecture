package com.wcp.tmdbcleanarchitecture

import android.app.Application
import com.wcp.tmdbcleanarchitecture.internal.di.components.ApplicationComponent
import com.wcp.tmdbcleanarchitecture.internal.di.components.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MovieApplication : Application(), HasAndroidInjector {
   /* val appComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }*/

    lateinit var applicationComponent : ApplicationComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>


    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        applicationComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()
        applicationComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    companion object {
        private var INSTANCE: MovieApplication? = null

        @JvmStatic
        fun get(): MovieApplication = INSTANCE!!
    }
}