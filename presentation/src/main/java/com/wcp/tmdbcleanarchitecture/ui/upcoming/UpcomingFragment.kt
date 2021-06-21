package com.wcp.tmdbcleanarchitecture.ui.upcoming

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wcp.domain.results.UpComingResult
import com.wcp.tmdbcleanarchitecture.databinding.FragmentUpcomingBinding
import com.wcp.tmdbcleanarchitecture.internal.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UpcomingFragment : Fragment() {

    private var _binding: FragmentUpcomingBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val upcomingViewModel: UpcomingViewModel by viewModels { viewModelFactory }

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

        upcomingViewModel.fetchUpComingMovies.observe(viewLifecycleOwner, {
            when (it) {
                is UpComingResult.Success -> {
                    Toast.makeText(context, it.data.results.toString(), Toast.LENGTH_SHORT).show()
                }
                is UpComingResult.FeatureFailure -> {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }


        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}