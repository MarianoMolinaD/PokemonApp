package com.portafoliowebmariano.pokedex.model.network

import com.portafoliowebmariano.pokedex.core.RetrofitHelper
import com.portafoliowebmariano.pokedex.model.data.PokemonData
import com.portafoliowebmariano.pokedex.model.data.colorpokemon.PokemonColorData
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.CurrentPokemonData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class PokemonServices @Inject constructor() {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAllPokemon(query: String): PokemonData {
        return withContext(Dispatchers.IO) {
            val response =
                retrofit.create(PokemonApiClient::class.java)
                    .getAllPokemon("pokemon?offset=0&limit=1025/$query")
            response.body()!!
        }
    }

    suspend fun getCurrentPokemon(query: String): CurrentPokemonData {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonApiClient::class.java).getCurrentPokemon(query)
            response.body()!!
        }
    }

    suspend fun getColorPokemon(query: String): PokemonColorData {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(PokemonApiClient::class.java)
                .getColorPokemon("pokemon-species/$query")
            response.body()!!
        }
    }
}