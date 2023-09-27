package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RecipeRequest(
    @SerializedName
        ("name") var recipe_name:String
    ,@SerializedName
        ("description")var recipe_content: String
) : Serializable {
}
