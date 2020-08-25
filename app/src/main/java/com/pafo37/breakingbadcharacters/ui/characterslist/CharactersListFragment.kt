package com.pafo37.breakingbadcharacters.ui.characterslist

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.pafo37.breakingbadcharacters.R
import com.pafo37.breakingbadcharacters.adapter.CharactersListAdapter
import com.pafo37.breakingbadcharacters.databinding.FragmentCharactersListBinding
import com.pafo37.breakingbadcharacters.ui.base.BaseFragment
import com.pafo37.breakingbadcharacters.viewmodel.characterslist.CharactersListViewModel

class CharactersListFragment :
    BaseFragment<FragmentCharactersListBinding, CharactersListViewModel>() {

    override val layoutId = R.layout.fragment_characters_list

    override val viewModelClass = CharactersListViewModel::class

    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(getString(R.string.characters_list_title))

        if (viewModel.currentCharactersList.isEmpty()) {
            viewModel.getCharacters()
        } else {
            updateSpinner(viewModel.getAvailableSeasons(viewModel.currentCharactersList))
        }

        val charactersAdapter = CharactersListAdapter(viewLifecycleOwner, viewModel)
        binding.recyclerViewCharacters.apply {
            layoutManager = GridLayoutManager(requireContext(), GRID_LAYOUT_SPAN_COUNT)
            adapter = charactersAdapter
        }

        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            charactersAdapter.charactersList = it
        })

        viewModel.navigateToCharacterDetails.observe(viewLifecycleOwner, Observer {
            if (findNavController().currentDestination?.id == R.id.characterListFragment) {
                findNavController().navigate(
                    CharactersListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(
                        it
                    )
                )
            }
        })

        viewModel.initializeSpinner.observe(viewLifecycleOwner, Observer {
            arrayAdapter = ArrayAdapter<String>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                setNotifyOnChange(true)
            }

            setSpinnerAdapter()

        })

        binding.searchViewCharacters.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty()) {
                    charactersAdapter.charactersList = viewModel.filterSearchList(newText)
                } else {
                    charactersAdapter.charactersList = viewModel.currentCharactersList
                }
                return true
            }
        })

    }

    private fun updateSpinner(availableSeasons: List<String>) {
        arrayAdapter.clear()
        arrayAdapter.addAll(availableSeasons)
        setSpinnerAdapter()
    }

    private fun setSpinnerAdapter() {
        binding.spinnerFilter.apply {
            adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //not used
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.filterCharactersBySeason(position)
                }
            }
        }
    }

    companion object {
        const val GRID_LAYOUT_SPAN_COUNT = 2
    }
}