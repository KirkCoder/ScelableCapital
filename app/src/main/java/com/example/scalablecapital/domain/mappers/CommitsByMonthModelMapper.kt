package com.example.scalablecapital.domain.mappers

import com.example.scalablecapital.domain.model.CommitModel
import com.example.scalablecapital.domain.model.CommitsByMonthModel
import java.util.*
import javax.inject.Inject

class CommitsByMonthModelMapper @Inject constructor() {

    fun map(commits: List<CommitModel>): List<CommitsByMonthModel> {
        return if (commits.isEmpty()) {
            emptyList()
        } else {
            val calendar = Calendar.getInstance()
            val res = LinkedList<CommitsByMonthModel>()
            calendar.time = commits.first().date
            res.add(
                CommitsByMonthModel(
                    month = calendar.get(Calendar.MONTH),
                    year = calendar.get(Calendar.YEAR),
                    count = 1
                )
            )

            for (i in 1 until commits.size) {
                val commit = commits[i]
                calendar.time = commit.date
                if (
                    res.last.month == calendar.get(Calendar.MONTH)
                    && res.last.year == calendar.get(Calendar.YEAR)
                ) {
                    val last = res.removeLast()
                    res.add(last.copy(count = last.count + 1))
                } else {
                    res.add(
                        CommitsByMonthModel(
                            month = calendar.get(Calendar.MONTH),
                            year = calendar.get(Calendar.YEAR),
                            count = 1
                        )
                    )
                }
            }
            res
        }
    }
}