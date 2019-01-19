package net.rubey.yoyocinema.di.favorites

import dagger.Subcomponent
import net.rubey.yoyocinema.favorites.FavoriteMoviesFragment

@FavoriteMoviesScope
@Subcomponent(modules = [FavoriteMoviesModule::class])
interface FavoriteMoviesComponent {
    fun inject(favoriteMoviesFragment: FavoriteMoviesFragment)
}