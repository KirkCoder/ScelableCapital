package com.example.scelablecapital.domain.mappers

import com.example.scelablecapital.domain.model.CommitByMonthWithPercentModel
import com.example.scelablecapital.domain.model.CommitsByMonthModel
import javax.inject.Inject

class CommitByMonthWithPercentModelMapper @Inject constructor() {
    fun map(commits: List<CommitsByMonthModel>): List<CommitByMonthWithPercentModel> {
        if (commits.isEmpty()) return emptyList()
        val max = commits.maxOf { commit -> commit.count }
        val onePercent = max.toDouble() / HUNDRED_PERCENT
        return commits.map { commit ->
            CommitByMonthWithPercentModel(
                commitsByMonthModel = commit,
                totalPercent = (commit.count.toDouble() / onePercent).toInt()
            )
        }
    }

    companion object {
        private const val HUNDRED_PERCENT = 100.0
    }
}