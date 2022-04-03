package com.example.scalablecapital.data.datastores

import com.example.scalablecapital.data.dto.CommitCoverageDto
import com.example.scalablecapital.network.GithubApi
import io.reactivex.Single
import javax.inject.Inject

class GetCommitsDataStore @Inject constructor(
    private val githubApi: GithubApi
) {
    fun getCommits(repositoryName: String): Single<List<CommitCoverageDto>> {
        return githubApi.getCommits(repositoryName)
    }
}