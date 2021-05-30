package com.lab.catreview.domain.usecase

import com.lab.catreview.domain.executor.SchedulerProvider
import io.reactivex.Observable

abstract class BaseUseCase<Result, in Params> constructor(private val scheduler: SchedulerProvider) {

    abstract fun buildUseCase(params: Params? = null): Observable<Result>

    fun execute(params: Params? = null): Observable<Result> {
        return buildUseCase(params)
            .subscribeOn(scheduler.run())
            .observeOn(scheduler.post())
    }
}