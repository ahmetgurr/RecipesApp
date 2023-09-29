package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.BaseRecipes
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeX
import com.example.sisterslabprojectrecipes.model.RecipesAnswer
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel @Inject constructor(var rrepo : RecipeRepository) : ViewModel() {
    var recipesList = MutableLiveData<List<Recipe>>()
    var recipeAdd = MutableLiveData<BaseRecipes?>()
    var recipeUpdate = MutableLiveData<BaseRecipes?>()
    var recipeSearch = MutableLiveData<RecipeX>()

    fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.getRecipes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipesList.value = response.body()?.recipes
                } else {
                }
            }
        }
    }

    suspend fun foodSearch(searchWord: String) {
        viewModelScope.launch {
            val response = rrepo.foodSearch(searchWord)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeSearch.value = response.body()
                } else {
                }
            }
        }
    }
}