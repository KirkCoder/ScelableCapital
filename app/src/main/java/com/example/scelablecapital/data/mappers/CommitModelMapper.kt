package com.example.scelablecapital.data.mappers

import com.example.scelablecapital.data.dto.CommitCoverageDto
import com.example.scelablecapital.data.dto.CommitDto
import com.example.scelablecapital.domain.model.CommitModel
import com.example.scelablecapital.utils.Safe
import javax.inject.Inject

class CommitModelMapper @Inject constructor(
    private val dateMapper: DateMapper,
) {

    fun map(dto: CommitCoverageDto): CommitModel? {
        return Safe {
            val commit = requireNotNull(dto.commit)
            map(commit)
        }.consumeError {
            // todo report health event
        }
    }

    private fun map(commit: CommitDto): CommitModel? {
        return Safe {
            val committer = requireNotNull(commit.committer)
            val dateString = requireNotNull(committer.date?.split("T")?.firstOrNull())
            val date = requireNotNull(dateMapper.map(dateString))
            CommitModel(
                date = date
            )
        }.consumeError {
            // todo report health event
        }
    }
}