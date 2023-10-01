package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.DetailResponse
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel  @Inject constructor (var rrepo : RecipeRepository): ViewModel() {

    var recipeDetail = MutableLiveData<DetailResponse?>()
    var recipeUpdateToast = MutableLiveData<String?>()

    suspend fun recipeDetail(recipeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.recipeDetail(recipeId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail.value = response.body()
                } else {
                }
            }
        }
    }
    suspend fun recipeUpdate(recipeId: Int, recipeName: String, recipeContent: String){
        val recipe = Recipe(recipeId,recipeName,recipeContent)
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.recipeUpdate(recipe)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeDetail(recipe.recipe_id)
                } else {
                    recipeUpdateToast.value = response.body()?.message
                }
            }
        }
    }
}