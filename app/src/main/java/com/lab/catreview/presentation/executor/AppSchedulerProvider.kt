package com.lab.catreview.presentation.executor

import com.lab.catreview.domain.executor.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSchedulerProvider @Inject
constructor() : SchedulerProvider {
    override fun run(): Scheduler = Schedulers.io()
    override fun post(): Scheduler = AndroidSchedulers.mainThread()
}