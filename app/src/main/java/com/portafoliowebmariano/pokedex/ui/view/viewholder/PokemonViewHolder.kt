package com.portafoliowebmariano.pokedex.ui.view.viewholder

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.pokedex.databinding.ItemPokemonBinding
import com.portafoliowebmariano.pokedex.model.data.Result
import com.squareup.picasso.Picasso

class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPokemonBinding.bind(view)

    fun bin(pokemon : Result, onclickItem: (Result) -> Unit){
        Log.i("miTag",pokemon.url)
        val number = extractNumber(pokemon.url)
        val urlImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon"
        binding.tvNamePokemon.text = pokemon.name
        Picasso.get().load("$urlImage/$number.png").into(binding.ivImagePokemon)
        binding.tvNumberPokemon.text = "#${formatNumber(number?.toInt() ?: 0)}"
        itemView.setOnClickListener {
            onclickItem(pokemon)
        }
    }
    fun extractNumber(url: String): Int? {
        val regex = Regex("/(\\d+)/$")
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value?.toInt()
    }
    fun formatNumber(num: Int): String {
        // Usar String.format para formatear el número con ceros adicionales según sea necesario
        return String.format("%03d", num)
    }
}