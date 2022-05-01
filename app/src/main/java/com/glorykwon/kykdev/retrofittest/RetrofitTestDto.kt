package com.glorykwon.kykdev.retrofittest

import com.google.gson.annotations.SerializedName

data class RetrofitTestDto(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("completed")
    val completed: Boolean
)