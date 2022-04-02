package com.example.scelablecapital.domain.usecases

import com.example.scelablecapital.domain.model.GitHubRepositoryModel
import com.example.scelablecapital.domain.repositories.GitHubRepositoriesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetAllGitHubRepositoriesUseCase @Inject constructor(
    private val gitHubRepositoriesRepository: GitHubRepositoriesRepository,
) {
    fun getAllGitHubRepositories(): Single<List<GitHubRepositoryModel>> {
        return gitHubRepositoriesRepository.getAllRepositories()
    }
}