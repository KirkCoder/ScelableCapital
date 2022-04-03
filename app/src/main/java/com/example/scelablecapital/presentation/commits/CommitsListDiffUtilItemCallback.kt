package com.example.scelablecapital.presentation.commits

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

object CommitsListDiffUtilItemCallback : DiffUtil.ItemCallback<CommitsByMonthPresentation>() {

    override fun areItemsTheSame(
        oldItem: CommitsByMonthPresentation,
        newItem: CommitsByMonthPresentation
    ): Boolean {
        return newItem.javaClass == oldItem.javaClass
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: CommitsByMonthPresentation,
        newItem: CommitsByMonthPresentation
    ): Boolean {
        return oldItem == newItem
    }

    override fun getChangePayload(
        oldItem: CommitsByMonthPresentation,
        newItem: CommitsByMonthPresentation
    ): Any? {
        return if (oldItem.isOnlyPercentChange(newItem)) {
            newItem
        } else {
            null
        }
    }
}