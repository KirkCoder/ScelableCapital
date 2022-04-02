package com.example.scelablecapital.data.mappers

import com.example.scelablecapital.data.dto.GitHubRepositoryDto
import com.example.scelablecapital.domain.model.GitHubRepositoryModel
import com.example.scelablecapital.utils.Safe
import javax.inject.Inject

class GitHubRepositoryModelMapper @Inject constructor() {

    fun map(dto: GitHubRepositoryDto): GitHubRepositoryModel? {
        return Safe {
            val id = requireNotNull(dto.id)
            val name = requireNotNull(dto.name)
            GitHubRepositoryModel(
                id = id,
                name = name,
            )
        }.consumeError { error ->
            //todo report error in health monitoring system
        }
    }
}