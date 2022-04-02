package com.example.scelablecapital.di

import com.example.App
import com.example.scelablecapital.base.BaseFragment
import com.example.scelablecapital.presentation.allrepositories.AllGitHubRepositoriesFragment
import com.example.scelablecapital.presentation.commits.CommitsFragment
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        AndroidModule::class,
        ApiModule::class,
        DataBindings::class,
        GsonModule::class,
    ]
)
@Singleton
interface AppComponent {
    fun inject(app: App)
    fun inject(baseFragment: BaseFragment)
    fun inject(commitsFragment: CommitsFragment)
    fun inject(allGitHubRepositoriesFragment: AllGitHubRepositoriesFragment)
}