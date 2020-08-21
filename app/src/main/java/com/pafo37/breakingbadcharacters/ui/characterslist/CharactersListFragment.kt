package com.pafo37.breakingbadcharacters.ui.characterslist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pafo37.breakingbadcharacters.R
import com.pafo37.breakingbadcharacters.adapter.CharactersListAdapter
import com.pafo37.breakingbadcharacters.databinding.FragmentCharactersListBinding
import com.pafo37.breakingbadcharacters.ui.base.BaseFragment
import com.pafo37.breakingbadcharacters.viewmodel.characterslist.CharactersListViewModel

class CharactersListFragment :
    BaseFragment<FragmentCharactersListBinding, CharactersListViewModel>() {

    override val layoutId = R.layout.fragment_characters_list

    override val viewModelClass = CharactersListViewModel::class

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(getString(R.string.characters_list_title))
        binding.recyclerViewCharacters.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            adapter = CharactersListAdapter(viewModel.list, viewLifecycleOwner, viewModel)
        }
    }
}