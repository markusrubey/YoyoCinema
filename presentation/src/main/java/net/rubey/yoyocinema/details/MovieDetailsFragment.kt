package net.rubey.yoyocinema.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_movie_details.*
import net.rubey.yoyocinema.App
import net.rubey.yoyocinema.R
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MovieDetailsViewModelFactory

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.createDetailsComponent()?.inject(this)

        viewModelFactory.movieId = arguments?.getInt(MOVIE_ID) ?: -1
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(this, Observer(this::showViewState))
        viewModel.favoriteState.observe(this, Observer(this::showFavoriteState))
        viewModel.getMovieDetails()

        favoriteMovieButton.setOnClickListener {
            viewModel.favoriteButtonClicked()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        App.releaseDetailsComponent()
    }

    private fun showViewState(state: MovieDetailsViewState) {
        val progressViewsVisibility = if (state.isLoading) View.VISIBLE else View.INVISIBLE
        val contentViewsVisibility = if (state.isLoading) View.INVISIBLE else View.VISIBLE

        progressBar.visibility = progressViewsVisibility
        moviePosterImageView.visibility = contentViewsVisibility
        movieTitleTextView.visibility = contentViewsVisibility
        movieOverviewTextView.visibility = contentViewsVisibility
        movieReleaseDateTextView.visibility = contentViewsVisibility
        favoriteMovieButton.visibility = contentViewsVisibility

        movieTitleTextView.text = state.movie?.title
        movieOverviewTextView.text = state.movie?.overview
        movieReleaseDateTextView.text = state.movie?.releaseDate

        Picasso.get().load(state.movie?.posterPath).into(moviePosterImageView)
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

    companion object {
        const val MOVIE_ID = "movieId"
    }
}
