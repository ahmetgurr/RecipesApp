package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.ViewModel
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import javax.inject.Inject

class AddViewModel  @Inject constructor(var rrepo : RecipeRepository): ViewModel() {

    fun save(recipe_name:String, recipe_content:String){
        rrepo.saveRecipe(recipe_name,recipe_content)
    }
}