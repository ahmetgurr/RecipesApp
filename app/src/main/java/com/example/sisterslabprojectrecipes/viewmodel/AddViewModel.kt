package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.ViewModel
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