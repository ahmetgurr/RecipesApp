package com.example.sisterslabprojectrecipes.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class AddViewModel  @Inject constructor(var rrepo : RecipeRepository): ViewModel() {

    fun save(recipeName:String, recipeContent:String){
        val recipe = RecipeRequest(recipeName,recipeContent)
        rrepo.saveRecipe(recipe)
    }
}