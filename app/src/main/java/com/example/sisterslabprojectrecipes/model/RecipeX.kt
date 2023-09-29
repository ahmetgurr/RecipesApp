package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//RecipeX classı Recipe classının listesini tutmak için kullanılacak, success ise silme işlemi için kullanılacak
data class RecipeX(
    @SerializedName("recipes")
    var recipes: List<Recipe>,
    @SerializedName("status")
    var status: Int
) : Serializable
