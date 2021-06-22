package com.wcp.tmdbcleanarchitecture.ui.popular

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
import com.wcp.tmdbcleanarchitecture.databinding.FragmentPopularBinding
import com.wcp.tmdbcleanarchitecture.internal.di.ViewModelFactory
import com.wcp.tmdbcleanarchitecture.models.PopularUIModel
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import com.wcp.tmdbcleanarchitecture.results.PopularUIResult
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PopularFragment : Fragment(), PopularMoviesAdapter.OnClickedListener {

    private var _binding: FragmentPopularBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val popularViewModel: PopularViewModel by viewModels { viewModelFactory }

    private val popularMoviesAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter(this)
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
        _binding = FragmentPopularBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observePopularMovies()
        observeFavoriteMovieActionState()

        binding.rvPopularMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = popularMoviesAdapter
        }

        binding.btnTryAgain.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            binding.btnTryAgain.visibility = View.GONE
            popularViewModel.fetchMovies()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).navView(isVisible = true)
    }

    private fun observePopularMovies() {
        binding.progressBar.visibility = View.VISIBLE
        binding.btnTryAgain.visibility = View.GONE
        popularViewModel.fetchPopularMovies.observe(viewLifecycleOwner, { state ->
            binding.progressBar.visibility = View.GONE
            when (state) {
                is PopularUIResult.Success -> {
                    popularMoviesAdapter.submitList(state.data)
                }
                is PopularUIResult.ServerError -> {
                    binding.btnTryAgain.visibility = View.VISIBLE
                    Toast.makeText(context, state.toString(), Toast.LENGTH_SHORT).show()
                }
                is PopularUIResult.NetworkConnectionFailure -> {
                    binding.btnTryAgain.visibility = View.VISIBLE
                    Toast.makeText(context, "Network Connection Failed.", Toast.LENGTH_SHORT).show()
                }
                is PopularUIResult.FeatureFailure -> {
                    binding.btnTryAgain.visibility = View.VISIBLE
                    Toast.makeText(context, "Unknown Error", Toast.LENGTH_SHORT).show()
                }
            }
        })

    }

    private fun observeFavoriteMovieActionState() {
        popularViewModel.favoriteMoviesUIState.observe(viewLifecycleOwner,{state->
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPosterClicked(popularUIModel: PopularUIModel) {
        val action = PopularFragmentDirections.actionNavigationPopularToMovieDetailsFragment(popularUIModel.id)
        findNavController().navigate(action)
        (activity as MainActivity).navView(isVisible = false)
    }

    override fun onFavoriteClicked(popularUIModel: PopularUIModel) {
        popularViewModel.addFavoriteMovie(popularUIModel)
    }
}