package com.example.scalablecapital.presentation.allrepositories

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class AllGitHubRepositoriesAdapter(
    onSelectCurrency: (GitHubRepositoryPresentation) -> Unit
) : ListDelegationAdapter<List<GitHubRepositoryPresentation>>() {

    init {
        delegatesManager.addDelegate(RepositoryDelegate(onSelectCurrency))
    }
}