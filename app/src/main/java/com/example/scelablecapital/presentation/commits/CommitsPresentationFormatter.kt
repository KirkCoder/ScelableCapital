package com.example.scelablecapital.presentation.commits

import com.example.scelablecapital.R
import com.example.scelablecapital.base.ResourceDataStore
import com.example.scelablecapital.domain.model.CommitModel
import javax.inject.Inject

class CommitsPresentationFormatter @Inject constructor(
    private val resourceDataStore: ResourceDataStore,
) {
    fun format(commits: List<CommitModel>): CommitsPresentation {
        return CommitsPresentation(
            commitsByMonth = emptyList(),
            totalCount = resourceDataStore.getString(R.string.commits_count, commits.size),
            showProgress = false,
            showError = false,
        )
    }
}