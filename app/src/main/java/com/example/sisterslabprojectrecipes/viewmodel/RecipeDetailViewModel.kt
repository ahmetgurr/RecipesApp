package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor (var rrepo : RecipeRepository): ViewModel() {

    var recipeDetail = MutableLiveData<Recipe?>()

    fun update(recipeId:Int, recipeName:String, recipeContent:String){
        val recipe = Recipe(recipeId, recipeName, recipeContent)
        rrepo.updateRecipe(recipe)
    }

    fun getDetail(recipeId:Int) {
        viewModelScope.launch {
            recipeDetail.value = rrepo.getRecipeDetail(recipeId)
        }
        //recipeDetail.value =  rrepo.getRecipeDetail(recipeId)
    }

    fun detail()  {
        recipeDetail.value =  rrepo.getDetail().value
    }
}