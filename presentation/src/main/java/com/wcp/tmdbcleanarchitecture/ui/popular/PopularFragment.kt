package com.wcp.tmdbcleanarchitecture.ui.popular

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.wcp.tmdbcleanarchitecture.databinding.FragmentPopularBinding
import com.wcp.tmdbcleanarchitecture.internal.di.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PopularFragment : Fragment() {

    private var _binding : FragmentPopularBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val popularViewModel: PopularViewModel by viewModels { viewModelFactory }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(layoutInflater,container,false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}