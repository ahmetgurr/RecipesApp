package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(var rrepo : RecipeRepository) : ViewModel() {
    var recipeList = MutableLiveData<List<Recipe>>()

    init {
        loadRecipe()
        recipeList = rrepo.getRecipes()
    }

    fun search(searchWord:String){
        rrepo.searchRecipe(searchWord)
    }

    fun delete(recipe_id: Int){
        rrepo.deleteRecipe(recipe_id)
    }

    fun loadRecipe(){
        rrepo.getAllRecipe()
    }

}