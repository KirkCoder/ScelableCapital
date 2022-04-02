package com.example.scelablecapital.network

import com.example.scelablecapital.data.dto.CommitCoverageDto
import com.example.scelablecapital.data.dto.CommitDto
import com.example.scelablecapital.data.dto.GitHubRepositoryDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    // todo in real application we should provide user name from authorization
    @GET("/users/mralexgray/repos")
    fun getAllRepositories(): Single<List<GitHubRepositoryDto>>

    @GET("/repos/mralexgray/{repositoryName}/commits")
    fun getCommits(@Path("repositoryName") repositoryName: String): Single<List<CommitCoverageDto>>
}