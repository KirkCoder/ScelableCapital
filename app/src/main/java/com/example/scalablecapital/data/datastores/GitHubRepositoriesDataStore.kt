package com.example.scalablecapital.data.datastores

import com.example.scalablecapital.data.dto.GitHubRepositoryDto
import com.example.scalablecapital.network.GithubApi
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepositoriesDataStore @Inject constructor(
    private val githubApi: GithubApi
) {
    fun getAllRepositories(): Single<List<GitHubRepositoryDto>> {
        return githubApi.getAllRepositories()
    }
}