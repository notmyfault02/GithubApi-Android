package com.example.android.presentation.model

import com.google.gson.annotations.SerializedName

class GithubOwner(
    val login: String,
    @SerializedName("avatar_url") val avataUrl: String
)