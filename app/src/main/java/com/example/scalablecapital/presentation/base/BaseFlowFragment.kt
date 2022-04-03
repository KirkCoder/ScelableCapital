package com.example.scalablecapital.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.scalablecapital.R
import com.example.scalablecapital.base.BaseFragment

abstract class BaseFlowFragment : BaseFragment() {

    protected abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    fun showFragment(
        toFragment: BaseFragment,
        tag: String
    ) {
        if (childFragmentManager.findFragmentByTag(tag) == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.content, toFragment, tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss()
        }
    }

    fun popBackStack(): Boolean {
        return if (childFragmentManager.backStackEntryCount > 1) {
            childFragmentManager.popBackStack()
            true
        } else {
            false
        }
    }
}
