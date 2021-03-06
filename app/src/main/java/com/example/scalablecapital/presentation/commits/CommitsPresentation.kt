package com.example.scalablecapital.presentation.commits

data class CommitsPresentation(
    val commitsByMonth: List<CommitsByMonthPresentation>,
    val totalCount: String,
    val showProgress: Boolean,
    val showError: Boolean,
)