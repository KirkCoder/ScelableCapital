package com.example.scelablecapital.domain.usecases

import com.example.scelablecapital.domain.model.CommitModel
import com.example.scelablecapital.domain.repositories.GetCommitsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCommitsUseCase @Inject constructor(
    private val commitsRepository: GetCommitsRepository
) {

    fun loadCommits(repositoryName: String): Single<List<CommitModel>> {
        return commitsRepository.loadCommits(repositoryName)
    }
}