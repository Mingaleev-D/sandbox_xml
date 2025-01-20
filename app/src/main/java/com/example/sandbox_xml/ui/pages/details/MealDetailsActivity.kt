package com.example.sandbox_xml.ui.pages.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.sandbox_xml.R
import com.example.sandbox_xml.data.model.RemoteRandomMeal
import com.example.sandbox_xml.databinding.ActivityMainBinding
import com.example.sandbox_xml.databinding.ActivityMealDetailsBinding
import com.example.sandbox_xml.ui.pages.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealDetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMealDetailsBinding.inflate(layoutInflater) }
    private val viewModel by viewModels<MealDetailsViewModel>()
    private lateinit var mealId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        getMealId()
        viewModel.getMealInformation(mealId)
        setMealInfo()
        binding.fab.setOnClickListener {
            saveMeal?.let { meal ->
                viewModel.saveMeal(meal)
            }

//            lifecycleScope.launch {
//                viewModel.getSavedAllMeals().collect {
//
//                }
//            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun getMealId() {
        val intent = intent
        mealId = intent.getStringExtra("id").toString()
    }

    private var saveMeal: RemoteRandomMeal.RemoteMeal? = null
    private fun setMealInfo() {
        viewModel.getMealInformation.observe(this) { meal ->
            meal?.meals?.let { data ->
                //
                //meal data model
                saveMeal = data[0]

                binding.apply {
                    collapsingToolbarLayout.title = data[0]!!.strMeal
                    categoryTxt.text = "Category: " + data[0]!!.strCategory
                    locationTxt.text = "Location: " + data[0]!!.strArea
                    instructionsDescripTxt.text = data[0]!!.strInstructions

                    videoImg.setOnClickListener {
                        val url = data[0]!!.strYoutube
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        startActivity(intent)
                    }

                    Glide.with(applicationContext).load(data[0]!!.strMealThumb).into(mealImg)
                }
            }
        }
    }
}
