package com.example.scelablecapital.data.datastores

import com.example.scelablecapital.data.dto.CommitCoverageDto
import com.example.scelablecapital.network.GithubApi
import io.reactivex.Single
import javax.inject.Inject

class GetCommitsDataStore @Inject constructor(
    private val githubApi: GithubApi
) {
    fun getCommits(repositoryName: String): Single<List<CommitCoverageDto>> {
        return githubApi.getCommits(repositoryName)
    }
}