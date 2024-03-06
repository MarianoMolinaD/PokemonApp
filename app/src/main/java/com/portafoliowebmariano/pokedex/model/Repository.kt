package com.portafoliowebmariano.pokedex.model

import android.util.Log
import com.portafoliowebmariano.pokedex.model.data.PokemonProvider
import com.portafoliowebmariano.pokedex.model.network.PokemonServices
import com.portafoliowebmariano.pokedex.model.data.Result
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.CurrentPokemonData
import javax.inject.Inject
import kotlin.math.log


class Repository @Inject constructor(
    private val api: PokemonServices
){

    private var providerAllPokemon: List<Result> = PokemonProvider.allPokemon

    suspend fun getAllPokemon(query: String): List<Result> {

        return try {
            if (providerAllPokemon.isNullOrEmpty()) {
                val response = api.getAllPokemon(query)
                val allPokemon = response.results
                providerAllPokemon = allPokemon
            } else {
                PokemonProvider.allPokemon = providerAllPokemon
            }
            PokemonProvider.allPokemon = providerAllPokemon
            providerAllPokemon
        } catch (e: Exception) {
            Log.i("Exception",e.toString())
            emptyList()
        }
        finally {
            Log.i("Exception","Error")

        }
    }

    suspend fun getCurrentPokemon(query: String): CurrentPokemonData? {
        return try {
            val response: CurrentPokemonData? = api.getCurrentPokemon(query)
            PokemonProvider.currentPokemon = response
            PokemonProvider.currentPokemon
        }catch (e: Exception){
            Log.i("Exception",e.toString())
            null
        }
        finally {
            Log.i("Exception","Error")

        }
    }

    suspend fun getColorPokemon(query: String): String {
        val response = api.getColorPokemon(query)
        return response.color.name
    }
}