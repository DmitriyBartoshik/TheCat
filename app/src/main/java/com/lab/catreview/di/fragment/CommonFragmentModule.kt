package com.lab.catreview.di.fragment


import com.lab.catreview.domain.executor.SchedulerProvider
import com.lab.catreview.presentation.executor.AppSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class CommonFragmentModule {
    @Provides
    fun provideScheduler(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}