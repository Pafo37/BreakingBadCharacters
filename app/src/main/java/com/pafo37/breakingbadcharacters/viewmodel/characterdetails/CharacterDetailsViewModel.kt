package com.pafo37.breakingbadcharacters.viewmodel.characterdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import javax.inject.Inject

class CharacterDetailsViewModel @Inject constructor() : ViewModel() {

    val characterDetails = MutableLiveData<CharactersListModel>()

    val image = characterDetails.map {
        it.img
    }

    val nickname = characterDetails.map {
        it.nickname
    }

    val name = characterDetails.map {
        it.name
    }

    val status = characterDetails.map {
        it.status
    }

    val occupation = characterDetails.map {
        it.occupation.joinToString(", ")
    }

    val seasonAppearance = characterDetails.map {
        it.appearance.joinToString(", ", "", "", -1, "") {
            "Season $it"
        }
    }
}