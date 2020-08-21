package com.pafo37.breakingbadcharacters.api

import javax.inject.Inject


class CharactersRepository @Inject constructor(private val charactersApi: CharactersApi) {

    suspend fun getCharacters() = charactersApi.getCharacters()

}