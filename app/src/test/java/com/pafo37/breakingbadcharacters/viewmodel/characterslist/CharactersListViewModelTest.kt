package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.pafo37.breakingbadcharacters.api.CharactersRepository
import com.pafo37.breakingbadcharacters.api.ResultOf
import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import com.pafo37.breakingbadcharacters.utils.CharacterConverter
import com.pafo37.breakingbadcharacters.utils.withObserver
import com.pafo37.breakingbadcharacters.viewmodel.BaseAppTest
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mock

class CharactersListViewModelTest : BaseAppTest() {

    @Mock
    lateinit var charactersRepository: CharactersRepository

    @Mock
    lateinit var characterConverter: CharacterConverter

    private lateinit var viewModel: CharactersListViewModel

    override fun setUp() {
        viewModel = CharactersListViewModel(
            charactersRepository,
            characterConverter
        )
    }

    @Test
    fun testGetCharacters() {
        runBlocking {
            whenever(charactersRepository.getCharacters()).thenReturn(
                ResultOf.Success(
                    listOf(
                        characterListResponse
                    )
                )
            )
            whenever(characterConverter.convertToCharactersListModel(listOf(characterListResponse))).thenReturn(
                listOf(characterListModel)
            )
            viewModel.getCharacters()
            viewModel.characterList.withObserver {
                verify(it).onChanged(mutableListOf(characterListModel))
            }

            viewModel.initializeSpinner.withObserver {
                verify(it).onChanged(listOf("All Seasons", "Season 1", "Season 2", "Season 3"))
            }
        }
    }

    @Test
    fun testViewsVisibility() {
        viewModel.getCharacters()
        viewModel.viewsVisibility.withObserver {
            verify(it).onChanged(true)
        }
    }

    @Test
    fun testOnCharacterClicked() {
        viewModel.onCharacterClicked(characterListModel)
        viewModel.navigateToCharacterDetails.withObserver {
            verify(it).onChanged(characterListModel)
        }
    }

    @Test
    fun testFilterCharactersBySeason() {
        runBlocking {
            whenever(charactersRepository.getCharacters()).thenReturn(
                ResultOf.Success(
                    listOf(
                        characterListResponse,
                        characterListResponseSeason5Appearance
                    )
                )
            )
            whenever(
                characterConverter.convertToCharactersListModel(
                    listOf(
                        characterListResponse,
                        characterListResponseSeason5Appearance
                    )
                )
            ).thenReturn(
                listOf(characterListModel, characterListModelSeason5Appearance)
            )
            viewModel.getCharacters()
            viewModel.filterCharactersBySeason(5)
            viewModel.characterList.withObserver {
                verify(it).onChanged(mutableListOf(characterListModelSeason5Appearance))
            }
        }
    }

    @Test
    fun testFilterCharactersByAllSeasons() {
        runBlocking {
            whenever(charactersRepository.getCharacters()).thenReturn(
                ResultOf.Success(
                    listOf(
                        characterListResponse,
                        characterListResponseSeason5Appearance
                    )
                )
            )
            whenever(
                characterConverter.convertToCharactersListModel(
                    listOf(
                        characterListResponse,
                        characterListResponseSeason5Appearance
                    )
                )
            ).thenReturn(
                listOf(characterListModel, characterListModelSeason5Appearance)
            )
            viewModel.getCharacters()
            viewModel.filterCharactersBySeason(0)
            viewModel.characterList.withObserver {
                verify(it).onChanged(
                    mutableListOf(
                        characterListModel,
                        characterListModelSeason5Appearance
                    )
                )
            }
        }
    }


    private val characterListModel =
        CharactersListModel(
            1, "Walter White", "01-01-1970", listOf("Drug Lord", "Chemistry Teacher"),
            "image", "Alive", "Walter", listOf(1, 2, 3), "portrayed", "category", listOf()
        )

    private val characterListResponse =
        CharactersResponse(
            1, "Walter White", "01-01-1970", listOf("Drug Lord", "Chemistry Teacher"),
            "image", "Alive", "Walter", listOf(1, 2, 3), "portrayed", "category", listOf()
        )

    private val characterListResponseSeason5Appearance =
        CharactersResponse(
            1, "Jessie", "01-01-1970", listOf("Drug Lord", "Chemistry Teacher"),
            "image", "Alive", "Jessie", listOf(5), "portrayed", "category", listOf()
        )
    private val characterListModelSeason5Appearance =
        CharactersListModel(
            1, "Jessie", "01-01-1970", listOf("Drug Lord", "Chemistry Teacher"),
            "image", "Alive", "Jessie", listOf(5), "portrayed", "category", listOf()
        )


}