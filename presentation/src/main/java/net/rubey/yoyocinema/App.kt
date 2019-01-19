package net.rubey.yoyocinema

import android.app.Application
import net.rubey.yoyocinema.di.AppComponent
import net.rubey.yoyocinema.di.DaggerAppComponent
import net.rubey.yoyocinema.di.details.MovieDetailsModule
import net.rubey.yoyocinema.di.details.MovieDetailsSubComponent
import net.rubey.yoyocinema.di.modules.AppModule
import net.rubey.yoyocinema.di.modules.DataModule
import net.rubey.yoyocinema.di.modules.NetworkModule

class App : Application() {

    lateinit var appComponent: AppComponent

    private var movieDetailsComponent: MovieDetailsSubComponent? = null

    override fun onCreate() {
        super.onCreate()

        initDependencies()
    }

    private fun initDependencies() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
            .dataModule(DataModule())
            .build()
    }

    fun createDetailsComponent(): MovieDetailsSubComponent {
        movieDetailsComponent = appComponent.plus(MovieDetailsModule())
        return movieDetailsComponent!!
    }

    fun releaseDetailsComponent() {
        movieDetailsComponent = null
    }
}