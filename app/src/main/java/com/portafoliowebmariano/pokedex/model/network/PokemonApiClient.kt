package com.portafoliowebmariano.pokedex.model.network

import com.portafoliowebmariano.pokedex.model.data.PokemonData
import com.portafoliowebmariano.pokedex.model.data.colorpokemon.PokemonColorData
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.CurrentPokemonData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonApiClient {
    @GET
    suspend fun getAllPokemon(@Url url: String): Response<PokemonData>

    @GET
    suspend fun getCurrentPokemon(@Url url: String): Response<CurrentPokemonData>

    @GET
    suspend fun getColorPokemon(@Url url: String): Response<PokemonColorData>
}