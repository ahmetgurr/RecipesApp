package com.example.sisterslabprojectrecipes.repository

import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.retrofit.RecipeDao

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