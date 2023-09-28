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

    var recipeList: MutableLiveData<List<Recipe>>



    init {
        recipeList = MutableLiveData()

    }

    fun getRecipes(): MutableLiveData<List<Recipe>> {
        return recipeList
    }

    /*
    //getRecipesById fonksiyonu ile veritabanından id'ye göre çekebilir
    fun getRecipeById(recipe_id:Int): MutableLiveData<List<Recipe>> {
        return recipeList
    }
     */
    //tarif kaydetmek için
    fun saveRecipe(recipe: RecipeRequest){
        rdao.addRecipe(recipe).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val response = response.body()
                Log.e("Tarif Kayıt"," ${response?.message} - ${response?.success}")
                getAllRecipe()
            }
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }
        })
    }

    fun updateRecipe(recipe: Recipe){
        rdao.updateRecipe(recipe).enqueue(object : Callback<BaseResponse> {
            override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                val response = response.body()
                Log.e("Tarif Guncelleme"," ${response?.message} - ${response?.success}")
                getAllRecipe()
            }
            override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            }

        })
    }
/*
    fun getRecipeDetail(id:Int){
        rdao.getRecipeDetail(id).enqueue(object : Callback<CRUD> {
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val response = response.body()
                Log.e("Tarif Guncelleme"," ${response?.message} - ${response?.success}")
                getAllRecipe()
            }

            override fun onFailure(call: Call<CRUD>, t: Throwable) {
            }

        })
    }
 */

//id hatası buradan devam et !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //tafif detayı için
    fun getRecipeDetail(id:Int) {
        rdao.recipeDetail(id).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                val response = response.body()!!.recipe
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
            }
        })
    }


    //tarif arama için
    fun searchRecipe(searchWord:String){
        rdao.searchRecipe(searchWord).enqueue(object : Callback<CRUD> {
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val liste = response.body()!!.recipe
                recipeList.value = liste
            }
            override fun onFailure(call: Call<CRUD>, t: Throwable) {
            }
        })
    }


/*
//tarif güncellemek için
fun updateRecipe(recipe_id: Int, recipe_name: String, recipe_content: String) {
        rdao.updateRecipe(recipe_id,recipe_name,recipe_content).enqueue(object : Callback<CRUD>{
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val succes = response.body()?.success
                val mesage = response.body()?.message
                Log.e("Tarif Güncelle", "$succes - $mesage")
                getAllRecipe()
            }
            override fun onFailure(call: Call<CRUD>, t: Throwable) {
            }
        })

    }
 */
    fun getAllRecipe(){
        rdao.allRecipe().enqueue(object : Callback<CRUD> {
            override fun onResponse(call: Call<CRUD>?, response: Response<CRUD>) {
                val list = response.body()!!.recipe
                recipeList.value = list
            }

            override fun onFailure(call: Call<CRUD>?, t: Throwable?) {
            }

        })
        Log.e("veriler","${rdao.allRecipe()}")
    }


}