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

    val recipeDetail = MutableLiveData<Recipe>()

    fun update(recipeId:Int, recipeName:String, recipeContent:String){
        val recipe = Recipe(recipeId, recipeName, recipeContent)
        rrepo.updateRecipe(recipe)
    }

    fun detail(recipeId:Int){
        val recipe = rrepo.getRecipeDetail(recipeId)

    }
}