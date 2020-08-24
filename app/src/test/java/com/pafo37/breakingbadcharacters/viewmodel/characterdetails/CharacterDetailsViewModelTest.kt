package com.pafo37.breakingbadcharacters.viewmodel.characterdetails

import com.nhaarman.mockitokotlin2.verify
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import com.pafo37.breakingbadcharacters.utils.withObserver
import com.pafo37.breakingbadcharacters.viewmodel.BaseAppTest
import org.junit.Test


class CharacterDetailsViewModelTest : BaseAppTest() {

    private lateinit var viewModel: CharacterDetailsViewModel

    override fun setUp() {
        viewModel = CharacterDetailsViewModel()
        viewModel.characterDetails.value = characterListModel
    }

    @Test
    fun testCharacterImage() {
        viewModel.image.withObserver {
            verify(it).onChanged("image")
        }
    }

    @Test
    fun testCharacterNickname() {
        viewModel.nickname.withObserver {
            verify(it).onChanged("Walter")
        }
    }

    @Test
    fun testCharacterName() {
        viewModel.name.withObserver {
            verify(it).onChanged("Walter White")
        }
    }

    @Test
    fun testCharacterStatus() {
        viewModel.status.withObserver {
            verify(it).onChanged("Alive")
        }
    }

    @Test
    fun testCharacterOccupation() {
        viewModel.occupation.withObserver {
            verify(it).onChanged("Drug Lord, Chemistry Teacher")
        }
    }

    @Test
    fun testCharacterSeasonAppearance() {
        viewModel.seasonAppearance.withObserver {
            verify(it).onChanged("Season 1, Season 2, Season 3")
        }
    }


    private val characterListModel =
        CharactersListModel(
            1, "Walter White", "01-01-1970", listOf("Drug Lord", "Chemistry Teacher"),
            "image", "Alive", "Walter", listOf(1, 2, 3), "portrayed", "category", listOf()
        )
}