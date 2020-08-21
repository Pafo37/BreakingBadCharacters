package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pafo37.breakingbadcharacters.api.CharactersRepository
import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel(), OnCharacterClicked {

    val list = mutableListOf<CharactersResponse>()

    val totalCharactersList = MutableLiveData<MutableList<CharactersResponse>>()

    val currentCharactersList = mutableListOf<CharactersResponse>()

    fun test() {

        viewModelScope.launch {
            try {
                val response = charactersRepository.getCharacters()
                if (response.isSuccessful) {
                    response.body()?.let {
                        currentCharactersList.addAll(it)
                        totalCharactersList.value = currentCharactersList
                        list.addAll(it)
                    }
                }
            } catch (exception: Exception) {
                Timber.d(exception)
            }
        }

    }

    override fun onCharacterClicked() {

    }
}