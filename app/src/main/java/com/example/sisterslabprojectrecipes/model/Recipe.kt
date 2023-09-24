package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Recipe(@SerializedName("recipe_id") var recipe_id:Int,
                  @SerializedName("recipe_name") var recipe_name:String,
                  @SerializedName("recipe_content")var recipe_content: String): Serializable {
}