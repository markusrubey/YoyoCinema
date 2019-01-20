package net.rubey.yoyocinema

import android.app.Application
import net.rubey.yoyocinema.di.AppComponent
import net.rubey.yoyocinema.di.DaggerAppComponent
import net.rubey.yoyocinema.di.details.MovieDetailsModule
import net.rubey.yoyocinema.di.details.MovieDetailsSubComponent
import net.rubey.yoyocinema.di.favorites.FavoriteMoviesComponent
import net.rubey.yoyocinema.di.favorites.FavoriteMoviesModule
import net.rubey.yoyocinema.di.modules.AppModule
import net.rubey.yoyocinema.di.modules.DataModule
import net.rubey.yoyocinema.di.modules.NetworkModule
import net.rubey.yoyocinema.di.search.MovieSearchComponent
import net.rubey.yoyocinema.di.search.MovieSearchModule

class App : Application() {

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

    companion object {

        private lateinit var appComponent: AppComponent

        private var movieDetailsComponent: MovieDetailsSubComponent? = null

        private var favoriteMoviesComponent: FavoriteMoviesComponent? = null

        private var movieSearchComponent: MovieSearchComponent? = null

        fun createDetailsComponent(): MovieDetailsSubComponent? {
            movieDetailsComponent = appComponent.plus(MovieDetailsModule())
            return movieDetailsComponent
        }

        fun releaseDetailsComponent() {
            movieDetailsComponent = null
        }

        fun createFavoriteMoviesComponent(): FavoriteMoviesComponent? {
            favoriteMoviesComponent = appComponent.plus(FavoriteMoviesModule())
            return favoriteMoviesComponent
        }

        fun releaseFavoriteMoviesComponent() {
            movieDetailsComponent = null
        }

        fun createMovieSearchComponent(): MovieSearchComponent? {
            movieSearchComponent = appComponent.plus(MovieSearchModule())
            return movieSearchComponent
        }

        fun releaseMovieSearchComponent() {
            movieSearchComponent = null
        }
    }
}