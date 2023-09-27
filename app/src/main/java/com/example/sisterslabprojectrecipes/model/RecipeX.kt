package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName

//RecipeX classı Recipe classının listesini tutmak için kullanılacak, success ise silme işlemi için kullanılacak
data class RecipeX (@SerializedName("recipe") var recipe:List<Recipe>,
               @SerializedName("succes")var success:Int) {//succes silme işlemi için kullanılacak
}