package com.wcp.tmdbcleanarchitecture.ui.upcoming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.wcp.tmdbcleanarchitecture.MainActivity
import com.wcp.tmdbcleanarchitecture.databinding.FragmentUpcomingBinding
import com.wcp.tmdbcleanarchitecture.internal.di.ViewModelFactory
import com.wcp.tmdbcleanarchitecture.models.UpComingUIModel
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import com.wcp.tmdbcleanarchitecture.results.UpComingUIResult
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UpcomingFragment : Fragment(), UpcomingMoviesAdapter.OnClickedListener {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val upcomingViewModel: UpcomingViewModel by viewModels { viewModelFactory }


    private val upcomingMoviesAdapter: UpcomingMoviesAdapter by lazy {
        UpcomingMoviesAdapter(this)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeUpcomingMovies()
        observeFavoriteMovieActionState()

        binding.rvUpcomingMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = upcomingMoviesAdapter
        }

        binding.btnTryAgain.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnTryAgain.visibility = View.GONE
            upcomingViewModel.fetchMovies()
        }

    }

    private fun observeFavoriteMovieActionState() {
        upcomingViewModel.favoriteMoviesUIState.observe(viewLifecycleOwner,{state->
            when(state){
                is FavoriteMoviesUIResult.SuccessAddFavoriteMovie->{
                    Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                }
                is FavoriteMoviesUIResult.SuccessDeleteFavoriteMovie->{
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                }
                is FavoriteMoviesUIResult.Failure->{
                    Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).navView(isVisible = true)
    }

    private fun observeUpcomingMovies() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnTryAgain.visibility = View.GONE
        upcomingViewModel.fetchUpComingMovies.observe(viewLifecycleOwner, { state ->
            binding.progressBar.visibility = View.GONE
            when (state) {
                is UpComingUIResult.Success -> {
                    upcomingMoviesAdapter.submitList(state.data)
                }
                is UpComingUIResult.ServerError -> {
                    Toast.makeText(context, state.toString(), Toast.LENGTH_SHORT).show()
                    binding.btnTryAgain.visibility = View.VISIBLE
                }
                is UpComingUIResult.NetworkConnectionFailure -> {
                    Toast.makeText(context, "Network Connection Failed.", Toast.LENGTH_SHORT).show()
                    binding.btnTryAgain.visibility = View.VISIBLE
                }
                is UpComingUIResult.FeatureFailure -> {
                    Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
                    binding.btnTryAgain.visibility = View.VISIBLE
                }
            }


        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPosterClicked(upComingUIModel: UpComingUIModel) {
        val action = UpcomingFragmentDirections.actionUpcomingMovieToDetailMovie(upComingUIModel.id)
        findNavController().navigate(action)
        (activity as MainActivity).navView(isVisible = false)
    }

    override fun onFavoriteClicked(upComingUIModel: UpComingUIModel) {
       upcomingViewModel.addFavoriteMovie(upComingUIModel)
    }
}