package com.example.scelablecapital.presentation.commits

data class CommitsByMonthPresentation(
    val month: String,
    val percent: Int,
){
    fun isOnlyPercentChange(other: CommitsByMonthPresentation): Boolean {
        return other.month == month && other.percent != percent
    }
}