package com.pafo37.breakingbadcharacters.di.module

import com.pafo37.breakingbadcharacters.utils.CharacterConverter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideCharacterConverter(): CharacterConverter = CharacterConverter()
}