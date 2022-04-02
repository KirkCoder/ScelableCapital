package com.example.scelablecapital.config

import io.reactivex.Scheduler

interface RxSchedulers {
    val io: Scheduler

    val computation: Scheduler

    val mainThread: Scheduler

    val trampoline: Scheduler
}