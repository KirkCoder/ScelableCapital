package com.example.scalablecapital.data.dto

import com.google.gson.annotations.SerializedName

data class GitHubRepositoryDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
)