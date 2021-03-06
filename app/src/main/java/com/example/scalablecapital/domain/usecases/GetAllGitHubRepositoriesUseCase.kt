package com.example.scalablecapital.domain.usecases

import com.example.scalablecapital.domain.model.GitHubRepositoryModel
import com.example.scalablecapital.domain.repositories.GitHubRepositoriesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllGitHubRepositoriesUseCase @Inject constructor(
    private val gitHubRepositoriesRepository: GitHubRepositoriesRepository,
) {
    fun getAllGitHubRepositories(): Single<List<GitHubRepositoryModel>> {
        return gitHubRepositoriesRepository.getAllRepositories()
    }
}