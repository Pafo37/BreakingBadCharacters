package com.pafo37.breakingbadcharacters.api

import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import javax.inject.Inject

class CharactersRepository @Inject constructor(private val charactersApi: CharactersApi) {

    suspend fun getCharacters(): ResultOf<List<CharactersResponse>> {
        return safeApiCall { charactersApi.getCharacters() }
    }

}