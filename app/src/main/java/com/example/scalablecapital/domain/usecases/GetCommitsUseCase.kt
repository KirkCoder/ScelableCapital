package com.example.scalablecapital.domain.usecases

import com.example.scalablecapital.domain.mappers.CommitByMonthWithPercentModelMapper
import com.example.scalablecapital.domain.mappers.CommitsByMonthModelMapper
import com.example.scalablecapital.domain.model.CommitsModel
import com.example.scalablecapital.domain.repositories.GetCommitsRepository
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