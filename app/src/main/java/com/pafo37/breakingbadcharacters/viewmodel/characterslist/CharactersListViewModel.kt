package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import androidx.lifecycle.ViewModel
import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked
import javax.inject.Inject

class CharactersListViewModel @Inject constructor() : ViewModel(), OnCharacterClicked {

    val list = listOf(
        CharactersResponse(
            1, "name", "1996-08031", listOf(),
            "", "", "", listOf(), "", "", listOf()
        ),CharactersResponse(
            1, "name", "1996-08031", listOf(),
            "", "", "", listOf(), "", "", listOf()
        ),CharactersResponse(
            1, "name", "1996-08031", listOf(),
            "", "", "", listOf(), "", "", listOf()
        ),CharactersResponse(
            1, "name", "1996-08031", listOf(),
            "", "", "", listOf(), "", "", listOf()
        )
    )

    override fun onCharacterClicked() {

    }
}