package com.example.sandbox_xml.ui.pages.home

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sandbox_xml.R
import com.example.sandbox_xml.databinding.FragmentHomeBinding
import com.example.sandbox_xml.ui.pages.details.MealDetailsActivity
import com.example.sandbox_xml.ui.pages.home.adapter.CategoryAdapter
import com.example.sandbox_xml.ui.pages.home.adapter.OverPopularAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding by lazy { _binding!! }
    private val viewModel by viewModels<HomeViewModel>()
    private lateinit var overAdapter: OverPopularAdapter
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreateView(
           inflater: LayoutInflater,
           container: ViewGroup?,
           savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
           view: View,
           savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        initRandImgView()

        initGetPopularOver()
        setupOverPopularRV()

        setupCategoriesRV()
        getAllCategories()
    }

    private fun setupCategoriesRV() {
        categoryAdapter = CategoryAdapter()
        binding.categoriesRv.apply {
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

    private fun getAllCategories() {
        lifecycleScope.launch {
            viewModel.getAllCategories.collect { listCategories ->
                listCategories?.let {
                    categoryAdapter.differ.submitList(it)
                }
            }
        }
    }

    private fun setupOverPopularRV() {
        overAdapter = OverPopularAdapter()
        binding.popularItemRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = overAdapter
        }
    }

    private fun initGetPopularOver() {
        viewModel.getOverPopular.observe(viewLifecycleOwner) { meal ->
            meal?.let { data ->
                overAdapter.differ.submitList(data)
            }
        }
    }

    private fun initRandImgView() {
        viewModel.getRandomMealLiveData.observe(viewLifecycleOwner) { meal ->
            meal?.let {
                Glide.with(this).load(meal.meals!![0]!!.strMealThumb).into(binding.cardImg)

                binding.cardImg.setOnClickListener {
                    val intent = Intent(context, MealDetailsActivity::class.java)
                    intent.putExtra("id", meal.meals[0]?.idMeal)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
