package net.rubey.yoyocinema.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import net.rubey.yoyocinema.App
import net.rubey.yoyocinema.R
import net.rubey.yoyocinema.details.MovieDetailsFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteMoviesFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var factory: FavoriteMoviesViewModelFactory

    private lateinit var favoriteViewModel: FavoriteMoviesViewModel

    private val favoriteMoviesAdapter = FavoriteMoviesAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.context.applicationContext as App).createFavoriteMoviesComponent()?.inject(this)

        favoriteViewModel = ViewModelProviders.of(this, factory).get(FavoriteMoviesViewModel::class.java)
        favoriteViewModel.viewState.observe(this, Observer(this::showViewState))
        favoriteViewModel.getFavoriteMovies()

        favoriteMoviesRecyclerView.layoutManager = GridLayoutManager(view.context, 3)
        favoriteMoviesRecyclerView.adapter = favoriteMoviesAdapter

        button.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_favoritesFragment_to_searchFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (view?.context?.applicationContext as App).releaseFavoriteMoviesComponent()
    }

    override fun onClick(view: View) {
        val movieId = view.tag as Int
        val bundle = bundleOf(MovieDetailsFragment.MOVIE_ID to movieId)
        Navigation.findNavController(view).navigate(R.id.action_favoritesFragment_to_detailsFragment, bundle)
    }

    private fun showViewState(favoriteMoviesViewState: FavoriteMoviesViewState) {
        favoriteMoviesViewState.movies?.let {
            favoriteMoviesAdapter.submitList(it)
        }
    }
}
