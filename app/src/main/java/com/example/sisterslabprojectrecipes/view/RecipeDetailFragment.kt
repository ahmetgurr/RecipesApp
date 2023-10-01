package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeDetailBinding
import com.example.sisterslabprojectrecipes.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private lateinit var binding: FragmentRecipeDetailBinding
    private lateinit var viewModel: RecipeDetailViewModel
    val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : RecipeDetailViewModel by viewModels ()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecipeDetailBinding.inflate(inflater,container,false)
        binding.viewModel = viewModel
        binding.recipeDetailToolbarName = "Tarif Detay"//binding ile ismi değişme

        val recipeId = args.recipeId

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.recipeDetail(recipeId)
        }

        viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
            binding.recipeObject = recipe?.recipe
        }

        binding.buttonUpdate.setOnClickListener {
            val recipeName = binding.editTextAddName.text.toString()
            val recipeContent = binding.editTextAddContent.text.toString()

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.recipeUpdate(args.recipeId, recipeName, recipeContent)
            }

            viewModel.recipeDetail.observe(viewLifecycleOwner) { recipe ->
                if (recipe != null) {
                    Toast.makeText(requireContext(), "Tarif Güncellendi", Toast.LENGTH_SHORT).show()
                    val action = RecipeDetailFragmentDirections.actionRecipeDetailFragmentToRecipeListFragment()
                    Navigation.findNavController(it).navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Tarif Güncellenmedi", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

}


