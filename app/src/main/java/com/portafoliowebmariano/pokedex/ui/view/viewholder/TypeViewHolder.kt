package com.portafoliowebmariano.pokedex.ui.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.pokedex.databinding.ItemTypeBinding
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.Type

class TypeViewHolder (view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemTypeBinding.bind(view)

    fun bin(type : Type){
        binding.tvType.text = type.type.name
    }
}