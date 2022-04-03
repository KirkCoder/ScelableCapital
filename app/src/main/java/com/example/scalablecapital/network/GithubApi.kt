package com.example.scalablecapital.network

import com.example.scalablecapital.data.dto.CommitCoverageDto
import com.example.scalablecapital.data.dto.GitHubRepositoryDto
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