package com.portafoliowebmariano.pokedex.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.pokedex.R
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.Type
import com.portafoliowebmariano.pokedex.ui.view.viewholder.TypeViewHolder

class TypeAdapter(private val type : List<Type>) : RecyclerView.Adapter<TypeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TypeViewHolder(layoutInflater.inflate(R.layout.item_type, parent, false))
    }

    override fun getItemCount(): Int = type.size

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val item : Type = type[position]
        holder.bin(item)
    }
}