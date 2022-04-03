package com.example.scalablecapital.presentation.allrepositories

data class AllRepositoriesPresentation(
    val repositories: List<GitHubRepositoryPresentation>,
    val showProgress: Boolean,
    val showError: Boolean,
)