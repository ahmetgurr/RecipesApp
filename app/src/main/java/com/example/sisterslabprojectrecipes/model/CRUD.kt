package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName

//SerializedName ad karışıklıklarında uyumu sağlamak için kullanılır.
//CRUD = Create, Read, Update, Delete
data class CRUD (@SerializedName("recipe") var recipe:List<Recipe>,
            @SerializedName("status") var success:Int,
            @SerializedName("message")var message:String){
}