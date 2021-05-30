package com.lab.catreview.di.app

import android.content.Context
import com.lab.catreview.app.App

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideContext(app: App): Context = app.applicationContext
}