package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//CRUD = Create, Read, Update, Delete
//SerializedName ad karışıklıklarında uyumu sağlamak için kullanılır.
data class BaseRecipe(
    @SerializedName("status")
    var status: Int,
    @SerializedName("message")
    var message: String
) : Serializable
