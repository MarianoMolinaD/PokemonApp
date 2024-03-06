package com.portafoliowebmariano.pokedex.model.data

import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.CurrentPokemonData
import com.portafoliowebmariano.pokedex.model.data.favoritespokemondata.FavoritesPokemonData

class PokemonProvider {
    companion object{
        var allPokemon : List<Result> = emptyList()

        var favoritesPokemon = mutableListOf<Result>()

        var currentPokemon : CurrentPokemonData? = null

        var pagCurrentPokemon : String = ""
    }
}