package com.example.scelablecapital.domain.repositories

import com.example.scelablecapital.domain.model.CommitModel
import io.reactivex.Single

interface GetCommitsRepository {
    fun loadCommits(repositoryName: String): Single<List<CommitModel>>
}