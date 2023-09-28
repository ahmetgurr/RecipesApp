package com.example.sisterslabprojectrecipes.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.sisterslabprojectrecipes.R
import com.example.sisterslabprojectrecipes.databinding.FragmentRecipeDetailBinding
import com.example.sisterslabprojectrecipes.viewmodel.RecipeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {
    private lateinit var tasarim: FragmentRecipeDetailBinding
    private lateinit var viewModel: RecipeDetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : RecipeDetailViewModel by viewModels ()
        viewModel = tempViewModel
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
0
        //tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_detail, container, false)
        //tasarim.viewModel= viewModel
        tasarim = FragmentRecipeDetailBinding.inflate(inflater,container,false)
        tasarim.viewModel = viewModel
        tasarim.recipeDetailToolbarName = "Tarif Detay"

        //this fragment is getting data from RecipeListFragment->
        //başka fragmentten gelen veriyi almak ve göstermek için(RecipeListFragment'den)
        val bundle: RecipeDetailFragmentArgs by navArgs()
        val gelenTarif = bundle.recipe
        tasarim.recipeObject = gelenTarif

        return tasarim.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)







    }
    fun buttonUpdate(recipe_id: Int, recipe_name: String, recipe_content:String) {
        viewModel.update(recipe_id,recipe_name,recipe_content)
    }
}