package com.wcp.tmdbcleanarchitecture.ui.movieDetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.wcp.domain.models.detail.MovieDetailDataModel
import com.wcp.domain.results.MovieDetailResult
import com.wcp.tmdbcleanarchitecture.databinding.FragmentMovieDetailsBinding
import com.wcp.tmdbcleanarchitecture.internal.di.ViewModelFactory
import com.wcp.tmdbcleanarchitecture.internal.extension.loadFromUrl
import com.wcp.tmdbcleanarchitecture.ui.upcoming.UpcomingFragmentArgs
import com.wcp.data.utils.getLocalTimeFromUnix
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }

    private val args: UpcomingFragmentArgs by navArgs()

    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = args.movieId


        requireActivity()
        observeFavoriteMovieActionState()
        viewModel.getMovieDetails(movieId).observe(viewLifecycleOwner, { state ->
            when (state) {
                is MovieDetailResult.Success -> {
                    bindDetailData(state.data)
                }
                is MovieDetailResult.ServerError -> {
                    Toast.makeText(context, state.toString(), Toast.LENGTH_SHORT).show()
                }
                is MovieDetailResult.NetworkConnectionFailure -> {
                    Toast.makeText(context, "Network Connection Failed", Toast.LENGTH_SHORT).show()
                }
                is MovieDetailResult.FeatureFailure -> {
                    Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun bindDetailData(movieDetailDataModel: MovieDetailDataModel) {

        binding.rating.isEnabled = false
        binding.rating.rating = (movieDetailDataModel.vote_average / 2).toFloat()
        binding.movieTitle.text = movieDetailDataModel.title

        binding.adult.visibility = if (movieDetailDataModel.adult) View.VISIBLE else View.GONE

        binding.overview.text = movieDetailDataModel.overview
        binding.orTitle.text = movieDetailDataModel.original_title

        binding.orLan.text =
            if (movieDetailDataModel.original_language == "en") "English" else movieDetailDataModel.original_language
        binding.txRelease.text = getLocalTimeFromUnix(movieDetailDataModel.release_date ?: "0000-00-00")

        binding.coverPicture.loadFromUrl(IMAGE_URL + movieDetailDataModel.backdrop_path)
        binding.ivPoster.loadFromUrl(IMAGE_URL + movieDetailDataModel.poster_path)

        binding.ivFavoriteMovie.setOnClickListener {
            viewModel.addFavoriteMovie(movieDetailDataModel)
        }

    }

    private fun observeFavoriteMovieActionState() {
        viewModel.favoriteMoviesUIState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is FavoriteMoviesUIResult.SuccessAddFavoriteMovie -> {
                    Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                }
                is FavoriteMoviesUIResult.SuccessDeleteFavoriteMovie -> {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                }
                is FavoriteMoviesUIResult.Failure -> {
                    Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}