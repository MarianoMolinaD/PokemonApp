package com.portafoliowebmariano.pokedex.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.pokedex.R
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.Ability
import com.portafoliowebmariano.pokedex.ui.view.viewholder.AbilitiesViewHolder

class AbilitiesAdapter(private val ability : List<Ability>): RecyclerView.Adapter<AbilitiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbilitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AbilitiesViewHolder(layoutInflater.inflate(R.layout.item_type, parent, false))
    }

    override fun getItemCount(): Int = ability.size

    override fun onBindViewHolder(holder: AbilitiesViewHolder, position: Int) {
        val item : Ability = ability[position]
        holder.bin(item)
    }
}