package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor (var rrepo : RecipeRepository): ViewModel() {

    fun update(recipeId:Int, recipeName:String, recipeContent:String){
        val recipe = Recipe(recipeId, recipeName, recipeContent)
        rrepo.updateRecipe(recipe)
    }

    //mutableLiveData kullan ve recipe tipinde bir değişken tanımla
    fun detail (id:Int) {
        val recipe = Recipe(id, recipe_name = "", recipe_content = "")
        rrepo.getRecipeDetail(id)
    }
}