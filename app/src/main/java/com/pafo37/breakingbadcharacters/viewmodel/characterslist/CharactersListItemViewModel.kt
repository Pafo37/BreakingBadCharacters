package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked

class CharactersListItemViewModel(val onCharacterClicked: OnCharacterClicked) : ViewModel() {

    val characters = MutableLiveData<CharactersResponse>()

    val url =
        MutableLiveData<String>("https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg")

    val name = MutableLiveData("Walter White Walter White ")

    fun onBind(model: CharactersResponse) {
        characters.value = model
    }
}