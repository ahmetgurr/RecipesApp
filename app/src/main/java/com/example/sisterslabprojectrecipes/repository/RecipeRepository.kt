package com.example.sisterslabprojectrecipes.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.sisterslabprojectrecipes.model.CRUD
import com.example.sisterslabprojectrecipes.model.Recipe
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
    fun saveRecipe(recipe_name:String, recipe_content:String){
        rdao.addRecipe(recipe_name,recipe_content).enqueue(object : Callback<CRUD> {
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val succes = response.body()!!.success
                val mesege = response.body()!!.message
                Log.e("Tarif Kayıt"," $succes - $mesege")
                getAllRecipe()
            }

            override fun onFailure(call: Call<CRUD>, t: Throwable) {
            }

        })
    }

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

    //tarif arama için
    fun searchRecipe(searchWord:String){
        rdao.searchRecipe(searchWord).enqueue(object : Callback<RecipeX> {
            override fun onResponse(call: Call<RecipeX>, response: Response<RecipeX>) {
                val liste = response.body()!!.recipe
                recipeList.value = liste
            }
            override fun onFailure(call: Call<RecipeX>, t: Throwable) {
            }
        })
    }

    //tarif silmek için
    fun deleteRecipe(recipe_id: Int){
        rdao.deleteRecipe(recipe_id).enqueue(object  : Callback<CRUD>{
            override fun onResponse(call: Call<CRUD>, response: Response<CRUD>) {
                val succes = response.body()!!.success
                val mesage = response.body()!!.message
                Log.e("Tarif Sil", "$succes - $mesage")
                getAllRecipe()
            }
            override fun onFailure(call: Call<CRUD>, t: Throwable) {
                Log.e("Tarifi Sil", "Tarif Silinemedi")
            }
        })
    }



/*
//nullPointerExeption hatası veriyor
    //tüm tarifleri çekmek için
    fun getAllRecipe(){
        rdao.allRecipe().enqueue(object : Callback<RecipeX> {
            override fun onResponse(call: Call<RecipeX>, response: Response<RecipeX>) {
                val liste = response.body()!!.recipe
                recipeList.value = liste
            }

            override fun onFailure(call: Call<RecipeX>, t: Throwable) {
            }

        })

    }
 */


/*
    //app açılırken boşsa nullPointerException hatasını gidermek için aşağıdaki gibi null kontrollü yazdık
    //suspend yapınca hata alıyorum

fun getAllRecipe() {
    rdao.allRecipe().enqueue(object : Callback<RecipeX> {
        override fun onResponse(call: Call<RecipeX>, response: Response<RecipeX>) {
            if (response.isSuccessful) {
                val liste = response.body()?.recipe
                if (liste != null) {
                    recipeList.value = liste
                } else {
                    // response.body()?.recipe null ise, uygun bir işlem yapılabilir
                }
            } else {
                // API isteği başarısız oldu
                // response.errorBody() gibi ek bilgilere erişebiliriz
            }
        }

        override fun onFailure(call: Call<RecipeX>, t: Throwable) {
            // API isteği tamamen başarısız oldu
        }
    })
}

 */

    fun getAllRecipe(){
        rdao.allRecipe().enqueue(object : Callback<RecipeX>{
            override fun onResponse(call: Call<RecipeX>?, response: Response<RecipeX>) {
                val liste = response.body()!!.recipe
                recipeList.value = liste
            }
            override fun onFailure(call: Call<RecipeX>?, t: Throwable?) {
            }

        })
    }
/*
    fun repicesGet(){

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


 */






}