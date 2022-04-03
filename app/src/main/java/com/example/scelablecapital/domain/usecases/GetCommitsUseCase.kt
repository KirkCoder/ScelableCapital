package com.example.scelablecapital.domain.usecases

import com.example.scelablecapital.domain.mappers.CommitByMonthWithPercentModelMapper
import com.example.scelablecapital.domain.mappers.CommitsByMonthModelMapper
import com.example.scelablecapital.domain.model.CommitByMonthWithPercentModel
import com.example.scelablecapital.domain.model.CommitsModel
import com.example.scelablecapital.domain.repositories.GetCommitsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCommitsUseCase @Inject constructor(
    private val commitsRepository: GetCommitsRepository,
    private val commitsByMonthModelMapper: CommitsByMonthModelMapper,
    private val commitByMonthWithPercentModelMapper: CommitByMonthWithPercentModelMapper,
) {

    fun loadCommits(repositoryName: String): Single<CommitsModel> {
        return commitsRepository.loadCommits(repositoryName).map { commits ->
            val commitsByMonth = commitsByMonthModelMapper.map(commits)
            CommitsModel(
                commitByMonthWithPercentModel = commitByMonthWithPercentModelMapper.map(
                    commitsByMonth
                ),
                totalCommitsCount = commits.size
            )
        }
    }
}