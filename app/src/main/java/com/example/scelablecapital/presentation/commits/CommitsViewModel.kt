package com.example.scelablecapital.presentation.commits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scelablecapital.base.BaseViewModel
import com.example.scelablecapital.config.RxSchedulers
import com.example.scelablecapital.domain.usecases.GetCommitsUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CommitsViewModel(
    private val repositoryName: String,
    private val getCommitsUseCase: GetCommitsUseCase,
    private val commitsPresentationFormatter: CommitsPresentationFormatter,
    rxSchedulers: RxSchedulers
) : BaseViewModel(rxSchedulers) {

    val allCommitsLiveData: LiveData<CommitsPresentation>
        get() = _allCommitsLiveData

    private val _allCommitsLiveData = MutableLiveData<CommitsPresentation>()

    val closeLiveData: LiveData<Any>
        get() = _closeLiveData

    private val _closeLiveData = MutableLiveData<Any>()

    init {
        if (repositoryName.isEmpty()) {
            showError()
            //todo should report this to our health monitoring system
            //todo should do another screen for this type of errors (with out retry button)
        } else {
            loadCommits()
        }
    }

    fun loadCommits() {
        getCommitsUseCase.loadCommits(repositoryName)
            .map(commitsPresentationFormatter::format)
            .repeatWhen { completed -> completed.delay(1500, TimeUnit.MICROSECONDS) }
            .toObservable()
            .schedule(
                onNext = { commits ->
                    showCommits(commits)
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

    private fun showCommits(commits: CommitsPresentation) {
        _allCommitsLiveData.value = commits
    }

    private fun showProgress() {
        _allCommitsLiveData.value = CommitsPresentation(
            commitsByMonth = emptyList(),
            totalCount = "",
            showProgress = true,
            showError = false
        )
    }

    private fun showError() {
        _allCommitsLiveData.value = CommitsPresentation(
            commitsByMonth = emptyList(),
            totalCount = "",
            showProgress = false,
            showError = true
        )
    }

    fun close() {
        _closeLiveData.value = Any()
    }

    class CommitsViewModelFactory @AssistedInject constructor(
        @Assisted("repositoryName") private val repositoryName: String,
        private val commitsPresentationFormatter: CommitsPresentationFormatter,
        private val getCommitsUseCase: GetCommitsUseCase,
        private val rxSchedulers: RxSchedulers,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CommitsViewModel(
                repositoryName = repositoryName,
                getCommitsUseCase = getCommitsUseCase,
                commitsPresentationFormatter = commitsPresentationFormatter,
                rxSchedulers = rxSchedulers
            ) as T
        }

        @AssistedFactory
        interface Assist {
            fun create(@Assisted("repositoryName") repositoryName: String): CommitsViewModelFactory
        }
    }

}