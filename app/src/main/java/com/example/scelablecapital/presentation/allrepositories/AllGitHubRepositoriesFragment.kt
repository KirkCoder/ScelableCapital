package com.example.scelablecapital.presentation.allrepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.App
import com.example.scelablecapital.MainActivity
import com.example.scelablecapital.R
import com.example.scelablecapital.base.BaseFragment
import androidx.fragment.app.viewModels
import com.example.scelablecapital.presentation.commits.CommitsFragment
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_all_repositories.*
import javax.inject.Inject

class AllGitHubRepositoriesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: AllGitHubRepositoryViewModel.AllGitHubRepositoryViewModelFactory

    private val viewModel: AllGitHubRepositoryViewModel by viewModels {
        viewModelFactory
    }

    private val allGitHubRepositoriesAdapter = AllGitHubRepositoriesAdapter(::onSelectRepository)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_repositories, container, false)
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
        with(allRepositoriesRecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = allGitHubRepositoriesAdapter
            ContextCompat.getDrawable(context, R.drawable.devider)?.let { devider ->
                val sideMargin = context.resources.getDimension(R.dimen.side_margin).toInt()
                addItemDecoration(
                    AllRepositoriesItemDecorator(
                        marginBottom = sideMargin,
                        sideMargin = sideMargin,
                        divider = devider,
                    )
                )
            }
        }
    }

    private fun initListeners() {
        retryButton.setOnClickListener {
            viewModel.loadAllRepositories()
        }

        closeButton.setOnClickListener {
            viewModel.close()
        }
    }

    private fun onSelectRepository(repository: GitHubRepositoryPresentation) {
        (activity as? MainActivity)?.showFragment(
            toFragment = CommitsFragment.newInstance(repository.name),
            tag = CommitsFragment.TAG,
        )
    }

    private fun subscribeUi() {
        observe(viewModel.allGitHubRepositoriesLiveData, ::handleRepositories)
        observe(viewModel.closeLiveData, ::close)
    }

    private fun handleRepositories(allRepositoriesPresentation: AllRepositoriesPresentation) {
        errorLayout.isVisible = allRepositoriesPresentation.showError
        progressBar.isVisible = allRepositoriesPresentation.showProgress
        if (!allRepositoriesPresentation.showProgress) {
            allRepositoriesRecyclerView.isVisible = true
            allGitHubRepositoriesAdapter.items = allRepositoriesPresentation.repositories
            allGitHubRepositoriesAdapter.notifyDataSetChanged() // todo in our case it is ok but in real application here will be a lot of items and we should use diff utils
        }
    }

    private fun close(any: Any) {
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance() = AllGitHubRepositoriesFragment()
        const val TAG = "AllGitHubRepositoriesFragment"
    }
}