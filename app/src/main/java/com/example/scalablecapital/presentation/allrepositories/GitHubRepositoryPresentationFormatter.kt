package com.example.scalablecapital.presentation.allrepositories

import com.example.scalablecapital.R
import com.example.scalablecapital.base.ResourceDataStore
import com.example.scalablecapital.domain.model.GitHubRepositoryModel
import javax.inject.Inject

class GitHubRepositoryPresentationFormatter @Inject constructor(
    private val resourceDataStore: ResourceDataStore,
) {
    fun format(gitHubRepositoryModels: List<GitHubRepositoryModel>): List<GitHubRepositoryPresentation> {
        return gitHubRepositoryModels.map { gitHubRepositoryModel ->
            GitHubRepositoryPresentation(
                id = resourceDataStore.getString(R.string.repository_id, gitHubRepositoryModel.id),
                presentationName = resourceDataStore.getString(
                    R.string.repository_name,
                    gitHubRepositoryModel.name
                ),
                name = gitHubRepositoryModel.name,
            )
        }
    }
}