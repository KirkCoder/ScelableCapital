package com.example.scalablecapital.presentation.allrepositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scalablecapital.base.BaseViewModel
import com.example.scalablecapital.config.RxSchedulers
import com.example.scalablecapital.domain.usecases.GetAllGitHubRepositoriesUseCase
import timber.log.Timber
import javax.inject.Inject

class AllGitHubRepositoryViewModel @Inject constructor(
    private val getAllGitHubRepositoriesUseCase: GetAllGitHubRepositoriesUseCase,
    private val gitHubRepositoryPresentationFormatter: GitHubRepositoryPresentationFormatter,
    rxSchedulers: RxSchedulers,
) : BaseViewModel(rxSchedulers) {

    val allGitHubRepositoriesLiveData: LiveData<AllRepositoriesPresentation>
        get() = _allGitHubRepositoriesLiveData

    private val _allGitHubRepositoriesLiveData = MutableLiveData<AllRepositoriesPresentation>()

    val closeLiveData: LiveData<Any>
        get() = _closeLiveData

    private val _closeLiveData = MutableLiveData<Any>()

    init {
        loadAllRepositories()
    }

    fun loadAllRepositories() {
        getAllGitHubRepositoriesUseCase.getAllGitHubRepositories()
            .map(gitHubRepositoryPresentationFormatter::format)
            .schedule(
                onSuccess = { repositories ->
                    showRepositories(repositories)
                },
                onSubscribe = {
                    showProgress()
                },
                onError = { error ->
                    showError()
                    Timber.e(error)
                }
            )
    }

    private fun showRepositories(repositories: List<GitHubRepositoryPresentation>) {
        _allGitHubRepositoriesLiveData.value = AllRepositoriesPresentation(
            repositories = repositories,
            showProgress = false,
            showError = false
        )
    }

    private fun showProgress() {
        _allGitHubRepositoriesLiveData.value = AllRepositoriesPresentation(
            repositories = emptyList(),
            showProgress = true,
            showError = false
        )
    }

    private fun showError() {
        _allGitHubRepositoriesLiveData.value = AllRepositoriesPresentation(
            repositories = emptyList(),
            showProgress = false,
            showError = true
        )
    }

    fun close() {
        _closeLiveData.value = Any()
    }

    class AllGitHubRepositoryViewModelFactory @Inject constructor(
        private val getAllGitHubRepositoriesUseCase: GetAllGitHubRepositoriesUseCase,
        private val gitHubRepositoryPresentationFormatter: GitHubRepositoryPresentationFormatter,
        private val rxSchedulers: RxSchedulers,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AllGitHubRepositoryViewModel(
                getAllGitHubRepositoriesUseCase = getAllGitHubRepositoriesUseCase,
                gitHubRepositoryPresentationFormatter = gitHubRepositoryPresentationFormatter,
                rxSchedulers = rxSchedulers,
            ) as T
        }
    }
}