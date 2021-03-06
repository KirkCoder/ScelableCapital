package com.example

import android.app.Application
import com.example.scalablecapital.BuildConfig
import com.example.scalablecapital.di.AndroidModule
import com.example.scalablecapital.di.AppComponent
import com.example.scalablecapital.di.DaggerAppComponent
import io.reactivex.exceptions.UndeliverableException
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initDi()
        initRxErrorHandler()
    }

    private fun initDi() {
        appComponent = DaggerAppComponent.builder()
            .androidModule(AndroidModule(this))
            .build()

        appComponent.inject(this)
    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { throwable ->
            Timber.tag("GlobalErrorHandler").e(throwable)

            if (throwable !is UndeliverableException && BuildConfig.DEBUG) {
                throw throwable
            }
        }
    }

    companion object {
        lateinit var instance: App
    }
}