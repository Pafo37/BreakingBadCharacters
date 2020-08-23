package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked

class CharactersListItemViewModel(private val onCharacterClicked: OnCharacterClicked) : ViewModel() {

    private val characters = MutableLiveData<CharactersListModel>()

    val url = characters.map {
        it.img
    }
    val name = characters.map {
        it.name
    }

    fun onBind(model: CharactersListModel) {
        characters.value = model
    }

    fun onCharacterClicked() {
        characters.value?.let {
            onCharacterClicked.onCharacterClicked(it)
        }
    }
}