package com.example.scelablecapital.di

import com.example.scelablecapital.config.RxSchedulers
import com.example.scelablecapital.config.RxSchedulersImpl
import com.example.scelablecapital.data.mappers.DateMapper
import com.example.scelablecapital.data.mappers.DateMapperImpl
import com.example.scelablecapital.data.repositories.GetCommitsRepositoryImpl
import com.example.scelablecapital.data.repositories.GitHubRepositoriesRepositoryImpl
import com.example.scelablecapital.domain.repositories.GetCommitsRepository
import com.example.scelablecapital.domain.repositories.GitHubRepositoriesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataBindings {

    @Binds
    abstract fun rxSchedulersImpl(rxSchedulersImpl: RxSchedulersImpl): RxSchedulers

    @Binds
    abstract fun allGitHubRepositoriesRepository(allGitHubRepositoriesRepository: GitHubRepositoriesRepositoryImpl): GitHubRepositoriesRepository

    @Binds
    abstract fun getCommitsRepository(getCommitsRepository: GetCommitsRepositoryImpl): GetCommitsRepository

    @Binds
    abstract fun dateMapper(dateMapper: DateMapperImpl): DateMapper

}