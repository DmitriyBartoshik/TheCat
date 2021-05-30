package com.lab.catreview.domain.executor

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun run(): Scheduler
    fun post(): Scheduler
}