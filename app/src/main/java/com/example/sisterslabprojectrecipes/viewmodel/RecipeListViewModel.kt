package com.example.sisterslabprojectrecipes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisterslabprojectrecipes.model.Recipe
import com.example.sisterslabprojectrecipes.model.RecipeX
import com.example.sisterslabprojectrecipes.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

//LiveDatalar verileri tutar ve gözlemlenebilir yapılardır. veri değişikliklerini iletir.
//viewModelScope = ViewModel'in yaşam döngüsü boyunca çalışır, sona erdiğninde otomatik olarak iptal edilir. veri kaybını önler.
// fragmentlerden bagımsızdır. ağ istekleri için kullanılır.
@HiltViewModel
class RecipeListViewModel @Inject constructor(var rrepo : RecipeRepository) : ViewModel() {
    var recipesList = MutableLiveData<List<Recipe>>()
    var recipeSearch = MutableLiveData<RecipeX>()

    fun getRecipes() {
        viewModelScope.launch(Dispatchers.IO) { //Ağ isteği / API'den veri çekme.
            val response = rrepo.getRecipes()
            withContext(Dispatchers.Main) {     //verileri alma
                if (response.isSuccessful) {                    //başarılı ise güncelleme
                    recipesList.value = response.body()?.recipes
                } else {
                }
            }
        }
    }

    suspend fun searchRecipe(searchWord: String) {
        viewModelScope.launch {
            val response = rrepo.recipeSearch(searchWord)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    recipeSearch.value = response.body()
                } else {
                }
            }
        }
    }
}