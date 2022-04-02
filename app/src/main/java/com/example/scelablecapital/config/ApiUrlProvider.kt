package com.example.scelablecapital.config

import javax.inject.Inject

class ApiUrlProvider @Inject constructor() {

    fun getUrlForConfig(apiUrlConfig: ApiUrlConfig): String {
        return apiUrlConfig.provideApi()
    }
}