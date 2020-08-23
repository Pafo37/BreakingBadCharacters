package com.pafo37.breakingbadcharacters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.pafo37.breakingbadcharacters.databinding.ItemCharactersListBinding
import com.pafo37.breakingbadcharacters.model.CharactersListModel
import com.pafo37.breakingbadcharacters.ui.characterslist.OnCharacterClicked
import com.pafo37.breakingbadcharacters.viewmodel.characterslist.CharactersListItemViewModel

class CharactersListAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onCharacterClicked: OnCharacterClicked
) : RecyclerView.Adapter<CharactersListAdapter.ItemViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemCharactersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewModel = CharactersListItemViewModel(onCharacterClicked)
        return ItemViewHolder(binding, viewModel)
    }

    override fun getItemId(position: Int) = charactersList[position].hashCode().toLong()

    var charactersList: MutableList<CharactersListModel> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = charactersList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind(charactersList[position])
    }

    inner class ItemViewHolder(
        private val binding: ItemCharactersListBinding,
        private val viewModel: CharactersListItemViewModel
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = viewModel
            binding.lifecycleOwner = lifecycleOwner
        }

        fun onBind(model: CharactersListModel) {
            viewModel.onBind(model)
            binding.executePendingBindings()
        }

    }
}