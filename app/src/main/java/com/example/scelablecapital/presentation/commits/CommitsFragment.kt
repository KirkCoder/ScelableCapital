package com.example.scelablecapital.presentation.commits

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.App
import com.example.scelablecapital.R
import com.example.scelablecapital.base.BaseFragment
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_commits.*
import javax.inject.Inject

class CommitsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: CommitsViewModel.CommitsViewModelFactory.Assist

    private val viewModel: CommitsViewModel by viewModels {
        viewModelFactory.create(arguments?.getString(ARGUMENTS) ?: "")
    }

    private val commitsAdapter = CommitsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun initDI() {
        super.initDI()
        App.instance.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initListeners()
        subscribeUi()
    }

    private fun initRecycler() {
        with(commitsRecyclerView) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = commitsAdapter
        }
    }

    private fun initListeners() {
        retryButton.setOnClickListener {
            viewModel.loadCommits()
        }

        closeButton.setOnClickListener {
            viewModel.close()
        }
    }

    private fun subscribeUi() {
        observe(viewModel.allCommitsLiveData, ::handleCommits)
        observe(viewModel.closeLiveData, ::close)
    }

    private fun handleCommits(commits: CommitsPresentation) {
        errorLayout.isVisible = commits.showError
        progressBar.isVisible = commits.showProgress
        if (!commits.showProgress) {
            totalTextView.text = commits.totalCount
            commitsRecyclerView.isVisible = true
            commitsAdapter.items = commits.commitsByMonth
        }
    }

    private fun close(any: Any) {
        activity?.onBackPressed()
    }

    companion object {
        const val TAG = "CommitsFragment"
        private const val ARGUMENTS = "ARGUMENTS"
        fun newInstance(name: String) = CommitsFragment().apply {
            arguments = Bundle().apply { putString(ARGUMENTS, name.trim()) }
        }
    }
}