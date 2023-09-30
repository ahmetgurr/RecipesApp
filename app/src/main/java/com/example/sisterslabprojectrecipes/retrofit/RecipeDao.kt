package com.example.sisterslabprojectrecipes.retrofit

import com.example.sisterslabprojectrecipes.model.BaseRecipe
import com.example.sisterslabprojectrecipes.model.DetailResponse
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeX
import com.example.sisterslabprojectrecipes.model.RecipesAnswer
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//Field =

// Query = veri çekmek ve işlemek için kullanılır
// GET veri çekmek
// POST veri yolllamak
// DAO Data Access Object= Veri Erişim Nesnesi /  veritabanı işlemlerini yönetmek için kullanılan bir tasarım desenidir
interface RecipeDao {


    @GET("get_recipes.php")
    suspend fun recipes(): Response<RecipesAnswer>

    @GET("search_recipe.php")
    suspend fun recipeSearch(@Query("query") query: String): Response<RecipeX>

    @GET("get_recipe_detail.php")
    suspend fun recipeDetail(@Query("id") id: Int): Response<DetailResponse>

    @POST("add_recipe.php")
    suspend fun addRecipe(@Body request: RecipeRequest): Response<BaseRecipe>

    @POST("update_recipe.php")
    suspend fun recipeUpdate(@Body request: Recipe): Response<BaseRecipe>

}