package com.lab.catreview.di.fragment

import com.lab.catreview.presentation.ui.fragment.common.CommonFragment
import com.lab.catreview.presentation.ui.fragment.favorite.FavoriteFragment
import com.lab.catreview.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector(modules= [CommonFragmentModule::class])
    abstract fun commonFragment() : CommonFragment

    @FragmentScope
    @ContributesAndroidInjector(modules= [FavoriteFragmentModule::class])
    abstract fun favoriteFragment() : FavoriteFragment
}