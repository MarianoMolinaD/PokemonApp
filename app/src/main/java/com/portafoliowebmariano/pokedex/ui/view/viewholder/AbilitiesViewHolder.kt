package com.portafoliowebmariano.pokedex.ui.view.viewholder


import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.pokedex.databinding.ItemTypeBinding
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.Ability

class AbilitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemTypeBinding.bind(view)

    fun bin(ability : Ability){
        binding.tvType.text = ability.ability.name
    }
}