package com.example.scalablecapital.domain.model

data class CommitsModel(
    val commitByMonthWithPercentModel: List<CommitByMonthWithPercentModel>,
    val totalCommitsCount: Int
)