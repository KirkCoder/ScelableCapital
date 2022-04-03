package com.example.scalablecapital.domain.repositories

import com.example.scalablecapital.domain.model.GitHubRepositoryModel
import io.reactivex.Single

interface GitHubRepositoriesRepository {
    fun getAllRepositories(): Single<List<GitHubRepositoryModel>>
}