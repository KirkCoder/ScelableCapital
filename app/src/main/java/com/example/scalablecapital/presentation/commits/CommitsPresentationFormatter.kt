package com.example.scalablecapital.presentation.commits

import com.example.scalablecapital.R
import com.example.scalablecapital.base.ResourceDataStore
import com.example.scalablecapital.domain.model.CommitsModel
import javax.inject.Inject

class CommitsPresentationFormatter @Inject constructor(
    private val resourceDataStore: ResourceDataStore,
) {
    fun format(commits: CommitsModel): CommitsPresentation {
        return CommitsPresentation(
            commitsByMonth = commits.commitByMonthWithPercentModel.map { commit ->
                CommitsByMonthPresentation(
                    month = getMothString(commit.commitsByMonthModel.month),
                    percent = commit.totalPercent
                )
            },
            totalCount = resourceDataStore.getString(
                R.string.commits_count,
                commits.totalCommitsCount
            ),
            showProgress = false,
            showError = false,
        )
    }

    private fun getMothString(month: Int): String {
        return when (month) {
            0 -> resourceDataStore.getString(R.string.january)
            1 -> resourceDataStore.getString(R.string.february)
            2 -> resourceDataStore.getString(R.string.march)
            3 -> resourceDataStore.getString(R.string.april)
            4 -> resourceDataStore.getString(R.string.may)
            5 -> resourceDataStore.getString(R.string.june)
            6 -> resourceDataStore.getString(R.string.july)
            7 -> resourceDataStore.getString(R.string.august)
            8 -> resourceDataStore.getString(R.string.september)
            9 -> resourceDataStore.getString(R.string.october)
            10 -> resourceDataStore.getString(R.string.november)
            else -> resourceDataStore.getString(R.string.december)
        }
    }
}