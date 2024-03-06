package com.portafoliowebmariano.pokedex.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.portafoliowebmariano.pokedex.model.data.Result
import com.portafoliowebmariano.pokedex.model.data.currentpokemondata.CurrentPokemonData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portafoliowebmariano.pokedex.model.Repository
import com.portafoliowebmariano.pokedex.model.data.PokemonProvider
import com.portafoliowebmariano.pokedex.ui.view.dialog.DialogShowErrorNetwork.showDialogErrorNet
import com.portafoliowebmariano.pokedex.ui.view.dialog.DialogShowInformation.showDialogInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var allPokemon = MutableLiveData<List<Result>>()
    var currentPokemon = MutableLiveData<CurrentPokemonData?>()
    var isLoading = MutableLiveData(true)
    var colorPokemon = MutableLiveData<String>()
    var bgLoading = MutableLiveData(false)
    var isLoadingAllPokemon = MutableLiveData(false)
    var isLoadingAllPokemonShimmer = MutableLiveData(true)

    fun getAllPokemon(query: String) {
        viewModelScope.launch {
            val response = repository.getAllPokemon(query)
            allPokemon.postValue(response)
            isLoadingAllPokemon.postValue(true)
            isLoadingAllPokemonShimmer.postValue(false)
        }
    }

    fun getCurrentPokemon(url: String) {
        bgLoading.postValue(true)
        viewModelScope.launch {
            val response = repository.getCurrentPokemon(url)
            currentPokemon.postValue(response)
            bgLoading.postValue(false)
        }

    }

    fun setFavoritesPokemon(): String {
        val provider = PokemonProvider
        Log.i("Prueba", provider.favoritesPokemon.toString())
        return when {
            provider.favoritesPokemon.isNullOrEmpty() -> {
                addFavorites()
            }

            provider.favoritesPokemon.none { it.name == provider.currentPokemon?.name } -> {
                addFavorites()
            }

            else -> "NO se agrego a favoritos "
        }
    }

    fun getColorPokemon(query: String) {
        viewModelScope.launch {
            colorPokemon.postValue(repository.getColorPokemon(query))
        }
    }

    fun addFavorites(): String {
        val provider = PokemonProvider
        val url = "https://pokeapi.co/api/v2/pokemon/${provider.currentPokemon!!.id}/"
        provider.favoritesPokemon.add(
            Result(provider.currentPokemon!!.name, url)
        )
        return "Se agrego ${provider.currentPokemon!!.name} a mis favoritos"
    }

    fun showDialogError(context: Context, message: String) {
        showDialogErrorNet(context, message)
    }
    fun showDialogInfo (context: Context){
        showDialogInformation(context)
    }
}