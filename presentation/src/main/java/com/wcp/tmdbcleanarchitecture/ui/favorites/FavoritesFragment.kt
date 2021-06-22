package com.wcp.tmdbcleanarchitecture.ui.favorites

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
import com.wcp.domain.models.FavoriteMovieDataModel
import com.wcp.tmdbcleanarchitecture.MainActivity
import com.wcp.tmdbcleanarchitecture.databinding.FragmentFavoritesBinding
import com.wcp.tmdbcleanarchitecture.internal.di.ViewModelFactory
import com.wcp.tmdbcleanarchitecture.results.FavoriteMoviesUIResult
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class FavoritesFragment : Fragment(), FavoriteMoviesAdapter.OnClickedListener {


    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: FavoritesViewModel by viewModels { viewModelFactory }

    private val favoriteMoviesAdapter : FavoriteMoviesAdapter by lazy {
        FavoriteMoviesAdapter(this)
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
        _binding = FragmentFavoritesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavoriteMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favoriteMoviesAdapter
        }
        viewModel.fetchFavoriteMovies.observe(viewLifecycleOwner,{state->
            when(state){
                is FavoriteMoviesUIResult.Success ->{
                    favoriteMoviesAdapter.submitList(state.data)
                }
                is FavoriteMoviesUIResult.Failure ->{
                    Toast.makeText(context, "Failure!", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).navView(isVisible = true)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPosterClicked(favoriteMovieDataModel: FavoriteMovieDataModel) {
        val action = FavoritesFragmentDirections.actionNavigationFavoritesToMovieDetailsFragment(favoriteMovieDataModel.id)
        findNavController().navigate(action)
        (activity as MainActivity).navView(isVisible = false)
    }

    override fun onFavoriteClicked(favoriteMovieDataModel: FavoriteMovieDataModel) {
        TODO("Not yet implemented")
    }
}