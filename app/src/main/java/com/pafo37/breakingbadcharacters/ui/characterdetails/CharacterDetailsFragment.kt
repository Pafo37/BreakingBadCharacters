package com.pafo37.breakingbadcharacters.ui.characterdetails

import android.os.Bundle
import android.view.View
import com.pafo37.breakingbadcharacters.R
import com.pafo37.breakingbadcharacters.databinding.FragmentCharacterDetailsBinding
import com.pafo37.breakingbadcharacters.ui.base.BaseFragment
import com.pafo37.breakingbadcharacters.viewmodel.CharacterDetailsViewModel

class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailsViewModel>() {

    override val layoutId = R.layout.fragment_character_details

    override val viewModelClass = CharacterDetailsViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}