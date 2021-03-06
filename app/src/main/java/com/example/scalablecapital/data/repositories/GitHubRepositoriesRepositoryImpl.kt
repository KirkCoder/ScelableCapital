package com.example.scalablecapital.data.repositories

import com.example.scalablecapital.data.datastores.GitHubRepositoriesDataStore
import com.example.scalablecapital.data.mappers.GitHubRepositoryModelMapper
import com.example.scalablecapital.domain.model.GitHubRepositoryModel
import com.example.scalablecapital.domain.repositories.GitHubRepositoriesRepository
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