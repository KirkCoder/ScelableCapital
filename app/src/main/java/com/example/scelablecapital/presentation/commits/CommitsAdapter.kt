package com.example.scelablecapital.presentation.commits

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class CommitsAdapter : ListDelegationAdapter<List<CommitsByMonthPresentation>>() {

    init {
        delegatesManager.addDelegate(CommitsDelegate())
    }
}