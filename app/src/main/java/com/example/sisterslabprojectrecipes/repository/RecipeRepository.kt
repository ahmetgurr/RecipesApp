package com.example.sisterslabprojectrecipes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sisterslabprojectrecipes.model.BaseResponse
import com.example.sisterslabprojectrecipes.model.CRUD
import com.example.sisterslabprojectrecipes.model.DetailResponse
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.model.RecipeX
import com.example.sisterslabprojectrecipes.retrofit.RecipeDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//DAO=Data Access Object
//DAO'ya erişim sağlayan sınıf
class RecipeRepository (var rdao : RecipeDao){


    suspend fun getRecipes() = rdao.recipes()
    //tarif kaydetmek için
    suspend fun addRecipe(request: RecipeRequest) = rdao.addRecipe(request)

    suspend fun recipeUpdate(recipe: Recipe) = rdao.recipeUpdate(recipe)

    suspend fun recipeDetail(id:Int) = rdao.recipeDetail(id)

    suspend fun foodSearch(searchWord:String) = rdao.foodSearch(searchWord)

}