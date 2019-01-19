package net.rubey.yoyocinema.di

import dagger.Component
import net.rubey.yoyocinema.di.details.MovieDetailsModule
import net.rubey.yoyocinema.di.details.MovieDetailsSubComponent
import net.rubey.yoyocinema.di.favorites.FavoriteMoviesComponent
import net.rubey.yoyocinema.di.favorites.FavoriteMoviesModule
import net.rubey.yoyocinema.di.modules.AppModule
import net.rubey.yoyocinema.di.modules.DataModule
import net.rubey.yoyocinema.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent {
    fun plus(movieDetailsModule: MovieDetailsModule): MovieDetailsSubComponent
    fun plus(favoriteMoviesModule: FavoriteMoviesModule): FavoriteMoviesComponent
}