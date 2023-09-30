package com.example.sisterslabprojectrecipes.repository

import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.retrofit.RecipeDao

//DAO=Data Access Object
//DAO'ya erişim sağlayan sınıf
class RecipeRepository (var rdao : RecipeDao){

    //tarifleri çekmek
    suspend fun getRecipes() = rdao.recipes()
    //tarif kaydetmek
    suspend fun addRecipe(request: RecipeRequest) = rdao.addRecipe(request)
    //tarif güncelemek
    suspend fun recipeUpdate(recipe: Recipe) = rdao.recipeUpdate(recipe)
    //tarif detayı çekmek
    suspend fun recipeDetail(id:Int) = rdao.recipeDetail(id)
    //tarif aramak
    suspend fun recipeSearch(searchWord:String) = rdao.recipeSearch(searchWord)

}