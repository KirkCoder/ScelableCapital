package com.example.scalablecapital.domain.model

data class CommitByMonthWithPercentModel(
    val commitsByMonthModel: CommitsByMonthModel,
    val totalPercent: Int,
)