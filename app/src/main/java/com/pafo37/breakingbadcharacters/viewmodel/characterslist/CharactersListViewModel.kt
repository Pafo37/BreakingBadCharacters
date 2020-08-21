package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pafo37.breakingbadcharacters.api.CharactersRepository
import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked
import com.pafo37.breakingbadcharacters.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CharactersListViewModel @Inject constructor(private val charactersRepository: CharactersRepository) :
    ViewModel(), OnCharacterClicked {

    val availableSeasons = mutableListOf<String>()
    val initializeSpinner = SingleLiveEvent<Unit>()
    val currentCharactersList = mutableListOf<CharactersResponse>()
    val characterList = MutableLiveData<MutableList<CharactersResponse>>()

    private val totalCharacterList = mutableListOf<CharactersResponse>()

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val response = charactersRepository.getCharacters()
                if (response.isSuccessful) {
                    response.body()?.let {
                        currentCharactersList.addAll(it)
                        characterList.value = currentCharactersList
                        totalCharacterList.addAll(it)
                        getAvailableSeasons(it)
                    }
                }
            } catch (exception: Exception) {
                Timber.d(exception)
            }
        }

    }

    override fun onCharacterClicked() {

    }

    private fun getAvailableSeasons(charactersList: List<CharactersResponse>) {
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