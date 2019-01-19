package net.rubey.yoyocinema.di.details

import dagger.Subcomponent
import net.rubey.yoyocinema.details.MovieDetailsFragment

@MovieDetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsSubComponent {
    fun inject(movieDetailsFragment: MovieDetailsFragment)
}