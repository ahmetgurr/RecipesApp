package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName

//SerializedName ad karışıklıklarında uyumu sağlamak için kullanılır.
//CRUD = Create, Read, Update, Delete
class CRUD (@SerializedName("succes") var success:Int,
            @SerializedName("message")var message:String){
}