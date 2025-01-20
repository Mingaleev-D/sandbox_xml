package com.example.sandbox_xml.ui.pages.favorite

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox_xml.R
import com.example.sandbox_xml.databinding.FragmentFavoriteBinding
import com.example.sandbox_xml.databinding.FragmentHomeBinding
import com.example.sandbox_xml.ui.pages.favorite.adapter.FavoriteAdapter
import com.example.sandbox_xml.ui.pages.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding by lazy { _binding!! }
    private val viewModel by viewModels<FavoriteViewModel>()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setupFavoriteRV()
        getSavedMeals()
    }

    private fun getSavedMeals() {
        lifecycleScope.launch {
            viewModel.getSavedAllMeals().collect { seavedMeals ->
                favoriteAdapter.differ.submitList(seavedMeals)
            }
        }

    }

    private fun setupFavoriteRV() {
        favoriteAdapter = FavoriteAdapter()
        binding.favoriteRv.apply {
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
            adapter = favoriteAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
