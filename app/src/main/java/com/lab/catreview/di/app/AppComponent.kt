package com.lab.catreview.di.app


import com.lab.catreview.app.App
import com.lab.catreview.di.ActivityBindingModule
import com.lab.catreview.di.DataModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        DataModule::class, AppModule::class
    ]
)
interface AppComponent {
    fun inject(application: App)
}