package com.example.sisterslabprojectrecipes.retrofit

import com.example.sisterslabprojectrecipes.model.BaseResponse
import com.example.sisterslabprojectrecipes.model.CRUD
import com.example.sisterslabprojectrecipes.model.RecipeRequest
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeX
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//Field =

// GET veri çekmek
// POST veri yolllamak
//DAO Data Access Object= Veri Erişim Nesnesi /  veritabanı işlemlerini yönetmek için kullanılan bir tasarım desenidir
interface RecipeDao {

    //https://api.canerture.com/recipes/get_recipes.php
    @GET("get_recipes.php")
    fun allRecipe(): Call<CRUD>
/*
    //https://api.canerture.com/recipes/get_recipe_detail.php?id=1
    @GET("get_recipe_detail.php")
     fun recipeDetail(
        @Query("query") query: String
     ): Call<CRUD>
 */

    //https://api.canerture.com/recipes/get_recipe_detail.php?id=1
    @GET("get_recipe_detail.php")
    fun recipeDetail(
        @Query("id") id: Int// int mi String mi bak!!!!!
    ): Call<CRUD>

    //https://api.canerture.com/recipes/search_recipe.php?query=mercimek
    @GET("search_recipe.php")
    fun searchRecipe(
        @Query("query") query: String
    ): Call<CRUD>

    //https://api.canerture.com/recipes/add_recipe.php?=
    @POST("add_recipe.php")
    fun addRecipe(
        @Body request : RecipeRequest
    ): Call<BaseResponse>

    //https://api.canerture.com/recipes/update_recipe.php
    @POST("update_recipe.php")
    fun updateRecipe(
        @Body request : Recipe
    ): Call<BaseResponse>

   /*
    //https://api.canerture.com/recipes/update_recipe.php
    @POST("update_recipe.php")
    @FormUrlEncoded
    fun updateRecipe(@Field("recipe_id") recipe_id: Int,
                     @Field("recipe_name") recipe_name: String,
                     @Field("recipe_content")recipe_content: String): Call<CRUD>
    */

}