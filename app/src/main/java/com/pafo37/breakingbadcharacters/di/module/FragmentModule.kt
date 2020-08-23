package com.pafo37.breakingbadcharacters.di.module

import com.pafo37.breakingbadcharacters.ui.characterslist.CharactersListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesCharactersListFragment(): CharactersListFragment
}