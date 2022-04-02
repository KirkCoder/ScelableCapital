package com.example.scelablecapital.domain.repositories

import com.example.scelablecapital.domain.model.GitHubRepositoryModel
import io.reactivex.Single

interface GitHubRepositoriesRepository {
    fun getAllRepositories(): Single<List<GitHubRepositoryModel>>
}