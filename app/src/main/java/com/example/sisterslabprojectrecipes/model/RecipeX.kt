package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName

class RecipeX (@SerializedName("recipe") var recipe:List<Recipe>,
               @SerializedName("succes")var success:Int) {//succes silme işlemi için kullanılacak
}