package com.portafoliowebmariano.pokedex.model.data.currentpokemondata

import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val black_white: BlackWhite
)