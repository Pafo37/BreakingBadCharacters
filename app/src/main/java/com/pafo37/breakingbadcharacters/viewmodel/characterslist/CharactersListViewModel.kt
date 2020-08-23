package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.pafo37.breakingbadcharacters.api.CharactersRepository
import com.pafo37.breakingbadcharacters.api.ResultOf
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked
import com.pafo37.breakingbadcharacters.utils.CharacterConverter
import com.pafo37.breakingbadcharacters.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val characterConverter: CharacterConverter
) : ViewModel(), OnCharacterClicked {

    val isLoading = MutableLiveData(false)
    val availableSeasons = mutableListOf<String>()
    val initializeSpinner = SingleLiveEvent<Unit>()
    val currentCharactersList = mutableListOf<CharactersListModel>()
    val characterList = MutableLiveData<MutableList<CharactersListModel>>()

    private val totalCharacterList = mutableListOf<CharactersListModel>()

    val viewsVisibility = isLoading.map {
        !it
    }

    fun getCharacters() {
        isLoading.value = true
        viewModelScope.launch {
            when (val response = charactersRepository.getCharacters()) {
                is ResultOf.Success -> {
                    val charactersList =
                        characterConverter.convertToCharactersListModel(response.data)
                    currentCharactersList.addAll(charactersList)
                    characterList.value = currentCharactersList
                    totalCharacterList.addAll(charactersList)
                    getAvailableSeasons(charactersList)
                }
                is ResultOf.Error -> {
                    Timber.d(response.message)
                }
            }
            isLoading.value = false
        }
    }

    override fun onCharacterClicked() {

    }

    private fun getAvailableSeasons(charactersList: List<CharactersListModel>) {
        availableSeasons.add("All Seasons")
        charactersList.map { it.appearance }.flatten().distinct().sorted().forEach {
            availableSeasons.add("Season $it")
        }
        initializeSpinner.call()
    }

    fun filterCharactersBySeason(season: Int) {
        if (season == 0) {
            characterList.value = totalCharacterList
        } else {
            val filteredList =
                currentCharactersList.filter { character -> character.appearance.any { it == season } }
                    .toMutableList()

            characterList.value = filteredList
        }
    }
}