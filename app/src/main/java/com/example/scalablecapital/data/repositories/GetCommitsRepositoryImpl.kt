package com.example.scalablecapital.data.repositories

import com.example.scalablecapital.data.datastores.GetCommitsDataStore
import com.example.scalablecapital.data.mappers.CommitModelMapper
import com.example.scalablecapital.domain.model.CommitModel
import com.example.scalablecapital.domain.repositories.GetCommitsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCommitsRepositoryImpl @Inject constructor(
    private val getCommitsDataStore: GetCommitsDataStore,
    private val commitModelMapper: CommitModelMapper,
) : GetCommitsRepository {

    override fun loadCommits(repositoryName: String): Single<List<CommitModel>> {
        return getCommitsDataStore.getCommits(repositoryName)
            .map { commits ->
                commits.mapNotNull { commit ->
                    commitModelMapper.map(commit)
                }
            }
    }
}