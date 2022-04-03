package com.example.scalablecapital.di

import com.example.scalablecapital.config.RxSchedulers
import com.example.scalablecapital.config.RxSchedulersImpl
import com.example.scalablecapital.data.mappers.DateMapper
import com.example.scalablecapital.data.mappers.DateMapperImpl
import com.example.scalablecapital.data.repositories.GetCommitsRepositoryImpl
import com.example.scalablecapital.data.repositories.GitHubRepositoriesRepositoryImpl
import com.example.scalablecapital.domain.repositories.GetCommitsRepository
import com.example.scalablecapital.domain.repositories.GitHubRepositoriesRepository
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