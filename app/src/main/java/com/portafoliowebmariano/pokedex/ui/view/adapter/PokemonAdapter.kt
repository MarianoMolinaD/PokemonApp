package com.portafoliowebmariano.pokedex.ui.view.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.pokedex.R
import com.portafoliowebmariano.pokedex.model.data.Result
import com.portafoliowebmariano.pokedex.ui.view.viewholder.PokemonViewHolder


class PokemonAdapter(private val pokemon : List<Result>, private val onclickItem: (Result) -> Unit ) : RecyclerView.Adapter<PokemonViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(layoutInflater.inflate(R.layout.item_pokemon, parent , false))
    }

    override fun getItemCount(): Int = pokemon.size

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item : Result = pokemon[position]
        holder.bin(item, onclickItem)
    }
}