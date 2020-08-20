package com.pafo37.breakingbadcharacters.di

import android.app.Application
import com.pafo37.breakingbadcharacters.App
import com.pafo37.breakingbadcharacters.di.module.ActivityModule
import com.pafo37.breakingbadcharacters.di.module.FragmentModule
import com.pafo37.breakingbadcharacters.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}