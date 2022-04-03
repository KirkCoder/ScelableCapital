package com.example.scalablecapital.domain.repositories

import com.example.scalablecapital.domain.model.CommitModel
import io.reactivex.Single

interface GetCommitsRepository {
    fun loadCommits(repositoryName: String): Single<List<CommitModel>>
}