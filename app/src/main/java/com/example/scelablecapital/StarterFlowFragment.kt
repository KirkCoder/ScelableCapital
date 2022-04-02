package com.example.scelablecapital

import android.os.Bundle
import android.view.View
import com.example.scelablecapital.presentation.allrepositories.AllGitHubRepositoriesFragment
import com.example.scelablecapital.presentation.base.BaseFlowFragment

class StarterFlowFragment : BaseFlowFragment() {

    override val layout: Int
        get() = R.layout.fragment_container

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showChildFragmentIfNeeded()
    }

    private fun showChildFragmentIfNeeded() {
        val fragment = childFragmentManager.findFragmentById(R.id.content)
        if (fragment == null) {
            showFragment(
                toFragment = AllGitHubRepositoriesFragment.newInstance(),
                tag = AllGitHubRepositoriesFragment.TAG
            )
        }
    }

    companion object {
        fun newInstance() = StarterFlowFragment()
    }

}