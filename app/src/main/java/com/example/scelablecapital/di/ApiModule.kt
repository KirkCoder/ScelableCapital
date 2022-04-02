package com.example.scelablecapital.di

import com.example.scelablecapital.config.ApiUrlConfig
import com.example.scelablecapital.config.ApiUrlProvider
import com.example.scelablecapital.network.GithubApi
import com.example.scelablecapital.network.LoggingInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun githubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    fun okHttpClient(
        loggingInterceptor: LoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun retrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        apiUrlProvider: ApiUrlProvider,
        apiUrlConfig: ApiUrlConfig
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrlProvider.getUrlForConfig(apiUrlConfig))
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    companion object {
        private const val TIMEOUT_SECONDS = 60L
    }
}