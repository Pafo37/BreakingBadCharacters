package com.pafo37.breakingbadcharacters.api

import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharactersApi {

    @GET("api/characters")
    suspend fun getCharacters(): Response<List<CharactersResponse>>
}