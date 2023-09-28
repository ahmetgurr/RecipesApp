package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName

data class DetailResponse (@SerializedName("recipe") var recipe:Recipe,
                           @SerializedName("status") var success:Int,
                           @SerializedName("message")var message:String){
}