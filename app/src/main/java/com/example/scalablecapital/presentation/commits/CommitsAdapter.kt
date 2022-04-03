package com.example.scalablecapital.presentation.commits

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class CommitsAdapter : AsyncListDifferDelegationAdapter<CommitsByMonthPresentation>(
    CommitsListDiffUtilItemCallback
) {

    init {
        delegatesManager.addDelegate(CommitsDelegate())
    }
}