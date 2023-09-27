package com.example.sisterslabprojectrecipes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BaseResponse(
    @SerializedName("status")
    var success:Int,
    @SerializedName("message")
    var message:String
): Serializable
