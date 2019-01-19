package net.rubey.yoyocinema.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_movie_details.*
import net.rubey.yoyocinema.App
import net.rubey.yoyocinema.R
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var factory: MovieDetailsViewModelFactory

    private lateinit var detailsViewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.context.applicationContext as App).createDetailsComponent()?.inject(this)

        factory.movieId = 369972
        detailsViewModel = ViewModelProviders.of(this, factory).get(MovieDetailsViewModel::class.java)
        detailsViewModel.viewState.observe(this, Observer(this::showViewState))
        detailsViewModel.favoriteState.observe(this, Observer(this::showFavoriteState))
        detailsViewModel.getMovieDetails()

        favoriteMovieButton.setOnClickListener {
            detailsViewModel.favoriteButtonClicked()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        (view?.context?.applicationContext as App).releaseDetailsComponent()
    }

    private fun showViewState(movieDetailsViewState: MovieDetailsViewState) {
        movieTitleTextView.text = movieDetailsViewState.title
    }

    private fun showFavoriteState(favorite: Boolean) {
        favoriteMovieButton.setImageResource(
            if (favorite) {
                R.drawable.ic_favorite_black_24dp
            } else {
                R.drawable.ic_favorite_border_black_24dp
            }
        )
    }
}
