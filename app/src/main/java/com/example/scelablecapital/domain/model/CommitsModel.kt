package com.example.scelablecapital.domain.model

data class CommitsModel(
    val commitByMonthWithPercentModel: List<CommitByMonthWithPercentModel>,
    val totalCommitsCount: Int
)