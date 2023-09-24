package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel  @Inject constructor(var rrepo : RecipeRepository): ViewModel() {

    fun save(recipe_name:String, recipe_content:String){
        rrepo.saveRecipe(recipe_name,recipe_content)
    }
}