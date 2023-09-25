package com.example.sisterslabprojectrecipes.retrofit

import com.example.sisterslabprojectrecipes.model.CRUD
import com.example.sisterslabprojectrecipes.model.RecipeX
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

// GET veri çekmek
// POST veri yolllamak
//DAO Data Access Object= Veri Erişim Nesnesi /  veritabanı işlemlerini yönetmek için kullanılan bir tasarım desenidir
interface RecipeDao {

    //https://api.canerture.com/recipes/get_recipes.php
    @GET("get_recipes.php")
    fun allRecipe(): Call<RecipeX>

    //mercimeği arıyor daha sonra düzelt !!!!!!!!!!!!!!!!
    //https://api.canerture.com/recipes/search_recipe.php?query=mercimek
    @POST("search_recipe.php?query=mercimek")
    @FormUrlEncoded
    fun searchRecipe(@Field("recipe_name")recipe_name: String): Call<RecipeX>

    //silme api si yok!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @POST("kisiler/delete_kisiler.php")
    @FormUrlEncoded
    fun deleteRecipe(@Field("recipe_id") recipe_id:Int): Call<CRUD>

    //https://api.canerture.com/recipes/add_recipe.php?=
    @POST("add_recipe.php?=")
    @FormUrlEncoded
    fun addRecipe(@Field("recipe_name")recipe_name: String,
                 @Field("recipe_content")recipe_content:String): Call<CRUD>

    //https://api.canerture.com/recipes/update_recipe.php
    @POST("update_recipe.php")
    @FormUrlEncoded
    fun updateRecipe(@Field("recipe_id") recipe_id: Int,
                     @Field("recipe_name") recipe_name: String,
                     @Field("recipe_content")recipe_content: String): Call<CRUD>



}