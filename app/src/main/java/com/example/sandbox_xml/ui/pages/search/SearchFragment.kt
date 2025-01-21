package com.example.sandbox_xml.ui.pages.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sandbox_xml.data.utils.hideKeyboard
import com.example.sandbox_xml.databinding.FragmentSearchBinding
import com.example.sandbox_xml.domain.model.RecentSearch
import com.example.sandbox_xml.ui.pages.search.adapter.RecentSearchListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding by lazy { _binding!! }
    private val viewModel by viewModels<SearchViewModel>()
    private val recentSearchAdapter: RecentSearchListAdapter = RecentSearchListAdapter()

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        setUiComponents()
        setObservers()
        setClickListeners()
    }

    private fun setUiComponents() {
        binding.rvRecentSearch.apply {
            adapter = recentSearchAdapter
        }
    }

    private fun setObservers() {
        viewModel.recentSearch.observe(viewLifecycleOwner) {
            recentSearchAdapter.submitList(it)
        }
    }

    private fun setClickListeners() {
        recentSearchAdapter.setRecentSearchClickListener {
            Toast.makeText(requireContext(), it.query, Toast.LENGTH_SHORT).show()
        }

        binding.cSearchView.etSearch.setOnEditorActionListener { _, _, _ ->
            hideKeyboard()
            viewModel.saveRecentSearch(
                   RecentSearch(query = binding.cSearchView.etSearch.text.toString())
            )
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
