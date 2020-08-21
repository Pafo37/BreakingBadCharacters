package com.pafo37.breakingbadcharacters.ui.characterslist

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.pafo37.breakingbadcharacters.R
import com.pafo37.breakingbadcharacters.adapter.CharactersListAdapter
import com.pafo37.breakingbadcharacters.api.response.CharactersResponse
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
        val charactersAdapter = CharactersListAdapter(viewLifecycleOwner, viewModel)
        binding.recyclerViewCharacters.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = charactersAdapter
        }

        viewModel.totalCharactersList.observe(viewLifecycleOwner, Observer {
            charactersAdapter.charactersList = it
        })

        binding.searchViewCharacters.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchList = arrayListOf<CharactersResponse>()
                if (!newText.isNullOrEmpty()) {
                    viewModel.currentCharactersList.map {
                        if (it.name.contains(newText, ignoreCase = true)) {
                            searchList.add(it)
                        }
                    }
                    charactersAdapter.charactersList = searchList
                    charactersAdapter.notifyDataSetChanged()
                } else {
                    charactersAdapter.charactersList = viewModel.currentCharactersList
                    charactersAdapter.notifyDataSetChanged()
                }
                return true
            }

        })

        viewModel.test()
    }
}