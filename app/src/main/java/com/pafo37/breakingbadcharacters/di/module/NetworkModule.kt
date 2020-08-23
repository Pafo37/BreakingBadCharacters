package com.pafo37.breakingbadcharacters.di.module

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.pafo37.breakingbadcharacters.BuildConfig
import com.pafo37.breakingbadcharacters.api.CharactersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun providesObjectMapper(): ObjectMapper {
        val objectMapper = jacksonObjectMapper()

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(
            DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE,
            true
        )

        return objectMapper
    }

    @Provides
    @Singleton
    internal fun providesJacksonConverterFactory(objectMapper: ObjectMapper): JacksonConverterFactory {
        return JacksonConverterFactory.create(objectMapper)
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        jacksonConverterFactory: JacksonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(jacksonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun providesCharactersApi(retrofit: Retrofit): CharactersApi {
        return retrofit.create(CharactersApi::class.java)
    }
}