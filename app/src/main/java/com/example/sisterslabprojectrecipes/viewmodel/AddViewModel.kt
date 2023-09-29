package com.example.sisterslabprojectrecipes.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.sisterslabprojectrecipes.model.BaseRecipe
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel

class AddViewModel  @Inject constructor(var rrepo : RecipeRepository): ViewModel() {

    val recipeAdd = MutableLiveData<BaseRecipe>()

    suspend fun addRecipes(foodName: String, recipe: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = rrepo.addRecipe(RecipeRequest(foodName, recipe))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeAdd.value = response.body()
                } else {

                }
            }
        }
    }
}