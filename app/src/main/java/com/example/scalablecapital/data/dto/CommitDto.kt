package com.example.scalablecapital.data.dto

import com.google.gson.annotations.SerializedName

data class CommitCoverageDto(
    @SerializedName("commit") val commit: CommitDto?,
)

data class CommitDto(
    @SerializedName("committer") val committer: CommitterDto?,
)

data class CommitterDto(
    @SerializedName("name") val name: String?,
    @SerializedName("date") val date: String?,
)