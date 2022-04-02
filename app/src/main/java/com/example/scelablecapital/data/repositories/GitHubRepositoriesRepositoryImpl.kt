package com.example.scelablecapital.data.repositories

import com.example.scelablecapital.data.datastores.GitHubRepositoriesDataStore
import com.example.scelablecapital.data.mappers.GitHubRepositoryModelMapper
import com.example.scelablecapital.domain.model.GitHubRepositoryModel
import com.example.scelablecapital.domain.repositories.GitHubRepositoriesRepository
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepositoriesRepositoryImpl @Inject constructor(
    private val gitHubRepositoriesDataStore: GitHubRepositoriesDataStore,
    private val gitHubRepositoryModelMapper: GitHubRepositoryModelMapper,
): GitHubRepositoriesRepository {

    override fun getAllRepositories(): Single<List<GitHubRepositoryModel>> {
        return gitHubRepositoriesDataStore.getAllRepositories()
            .map { repositories ->
                repositories.mapNotNull { repository ->
                    gitHubRepositoryModelMapper.map(repository)
                }
            }
    }
}