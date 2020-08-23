package com.pafo37.breakingbadcharacters.utils

import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import javax.inject.Singleton

@Singleton
class CharacterConverter {

    fun convertToCharactersListModel(charactersResponseList: List<CharactersResponse>): List<CharactersListModel> {
        return charactersResponseList.map { charactersResponse ->
            CharactersListModel(
                id = charactersResponse.id,
                name = charactersResponse.name,
                birthday = charactersResponse.birthday,
                occupation = charactersResponse.occupation,
                img = charactersResponse.img,
                status = charactersResponse.status,
                nickname = charactersResponse.nickname,
                appearance = charactersResponse.appearance,
                portrayed = charactersResponse.portrayed,
                category = charactersResponse.category,
                betterCallSaulAppearance = charactersResponse.betterCallSaulAppearance
            )
        }
    }
}