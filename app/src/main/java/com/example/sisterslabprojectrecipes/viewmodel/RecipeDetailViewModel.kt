package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor (var rrepo : RecipeRepository): ViewModel() {
    fun update(recipe_id:Int, recipe_name:String, recipe_content:String){
        rrepo.updateRecipe(recipe_id,recipe_name,recipe_content)
    }
}