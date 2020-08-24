package com.pafo37.breakingbadcharacters.viewmodel.characterslist

import com.pafo37.breakingbadcharacters.api.CharactersRepository
import com.pafo37.breakingbadcharacters.utils.CharacterConverter
import com.pafo37.breakingbadcharacters.viewmodel.BaseAppTest
import org.mockito.Mock

class CharactersListViewModelTest : BaseAppTest() {

    @Mock
    lateinit var charactersRepository: CharactersRepository

    @Mock
    lateinit var characterConverter: CharacterConverter

    private lateinit var viewModel: CharactersListViewModel

    override fun setUp() {
        viewModel = CharactersListViewModel(charactersRepository, characterConverter)
    }



}