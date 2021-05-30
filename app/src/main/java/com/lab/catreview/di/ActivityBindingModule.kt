package com.lab.catreview.di

import com.lab.catreview.di.fragment.FragmentBindingModule
import com.lab.catreview.presentation.ui.MainActivity
import com.lab.catreview.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [ FragmentBindingModule::class])
    internal abstract fun mainActivity(): MainActivity
}

