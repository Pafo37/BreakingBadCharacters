package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import com.nhaarman.mockitokotlin2.verify
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked
import com.pafo37.breakingbadcharacters.utils.withObserver
import com.pafo37.breakingbadcharacters.viewmodel.BaseAppTest
import org.junit.Test
import org.mockito.Mock

class CharacterListItemViewModelTest : BaseAppTest() {

    @Mock
    lateinit var onCharacterClicked: OnCharacterClicked

    private lateinit var viewModel: CharactersListItemViewModel

    override fun setUp() {
        viewModel = CharactersListItemViewModel(onCharacterClicked)
    }

    @Test
    fun testCharacterName() {
        viewModel.onBind(characterListModel)
        viewModel.name.withObserver {
            verify(it).onChanged("Walter White")
        }
    }

    @Test
    fun testCharacterImage() {
        viewModel.onBind(characterListModel)
        viewModel.image.withObserver {
            verify(it).onChanged("image")
        }
    }

    @Test
    fun testOnCharacterClicked() {
        viewModel.onBind(characterListModel)
        viewModel.onCharacterClicked()
        verify(onCharacterClicked).onCharacterClicked(characterListModel)
    }

    private val characterListModel =
        CharactersListModel(
            1, "Walter White", "01-01-1970", listOf("Drug Lord", "Chemistry Teacher"),
            "image", "Alive", "Walter", listOf(1, 2, 3), "portrayed", "category", listOf()
        )
}