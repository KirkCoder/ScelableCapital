package com.example.scelablecapital.domain.model

data class CommitByMonthWithPercentModel(
    val commitsByMonthModel: CommitsByMonthModel,
    val totalPercent: Int,
)