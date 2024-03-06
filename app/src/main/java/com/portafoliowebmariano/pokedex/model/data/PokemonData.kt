package com.portafoliowebmariano.pokedex.model.data

data class PokemonData(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)