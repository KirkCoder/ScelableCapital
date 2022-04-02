package com.example.scelablecapital.data.repositories

import com.example.scelablecapital.data.datastores.GetCommitsDataStore
import com.example.scelablecapital.data.mappers.CommitModelMapper
import com.example.scelablecapital.domain.model.CommitModel
import com.example.scelablecapital.domain.repositories.GetCommitsRepository
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