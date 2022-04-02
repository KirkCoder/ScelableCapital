package com.example.scelablecapital.data.datastores

import com.example.scelablecapital.data.dto.GitHubRepositoryDto
import com.example.scelablecapital.network.GithubApi
import io.reactivex.Single
import javax.inject.Inject

class GitHubRepositoriesDataStore @Inject constructor(
    private val githubApi: GithubApi
) {
    fun getAllRepositories(): Single<List<GitHubRepositoryDto>> {
        return githubApi.getAllRepositories()
    }
}