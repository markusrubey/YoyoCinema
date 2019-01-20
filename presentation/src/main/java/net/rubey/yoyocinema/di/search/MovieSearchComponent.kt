package net.rubey.yoyocinema.di.search

import dagger.Subcomponent
import net.rubey.yoyocinema.search.MovieSearchFragment

@MovieSearchScope
@Subcomponent(modules = [MovieSearchModule::class])
interface MovieSearchComponent {
    fun inject(movieSearchFragment: MovieSearchFragment)
}