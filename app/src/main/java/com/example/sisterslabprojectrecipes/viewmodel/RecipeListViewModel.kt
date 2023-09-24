package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(var rrepo : RecipeRepository) : ViewModel() {
    var kisilerListesi = MutableLiveData<List<Recipe>>()

    init {
        loadRecipe()
        kisilerListesi = rrepo.getRecipes()
    }

    fun search(searchWord:String){
        rrepo.searchRecipe(searchWord)
    }

    fun delete(recipe_name: Int){
        rrepo.deleteRecipe(recipe_name)
    }

    fun loadRecipe(){
        rrepo.getAllRecipe()
    }

}