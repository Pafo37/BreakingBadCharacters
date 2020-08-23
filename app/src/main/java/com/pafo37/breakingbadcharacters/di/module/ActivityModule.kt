package com.pafo37.breakingbadcharacters.di.module

import com.pafo37.breakingbadcharacters.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity
}