package com.portafoliowebmariano.pokedex.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.portafoliowebmariano.pokedex.R
import com.portafoliowebmariano.pokedex.databinding.FragmentAllPokemonBinding
import com.portafoliowebmariano.pokedex.model.Repository
import com.portafoliowebmariano.pokedex.model.data.PokemonProvider
import com.portafoliowebmariano.pokedex.ui.view.adapter.PokemonAdapter
import java.security.Provider
import com.portafoliowebmariano.pokedex.model.data.Result
import com.portafoliowebmariano.pokedex.model.network.PokemonServices
import com.portafoliowebmariano.pokedex.viewmodel.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllPokemonFragment : Fragment() {

    private lateinit var bindig: FragmentAllPokemonBinding
    private lateinit var adapter: PokemonAdapter
    private val pokemonViewModel: PokemonViewModel by viewModels()
    private val provider = PokemonProvider
    val manager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindig = FragmentAllPokemonBinding.inflate(inflater, container, false)

        return bindig.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        observer()
    }

    private fun observer() {
        pokemonViewModel.allPokemon.observe(viewLifecycleOwner) {
            adapter = PokemonAdapter(it) { pokemon ->
                onItemSelected(pokemon)
            }
            bindig.rvPokemon.layoutManager = manager
            bindig.rvPokemon.adapter = adapter
        }

        pokemonViewModel.isLoadingAllPokemon.observe(viewLifecycleOwner){
            bindig.rvPokemon.isVisible = it
        }
        pokemonViewModel.isLoadingAllPokemonShimmer.observe(viewLifecycleOwner){
            bindig.sflLoading.isVisible = it
        }
        pokemonViewModel.isLoadingAllPokemonShimmer.observe(viewLifecycleOwner){
            if(!it){
                if (provider.allPokemon.isNullOrEmpty()){
                    pokemonViewModel.showDialogError(requireContext(),"El servidor no se ha encontrado")
                }
            }
        }
        bindig.ivInformation.setOnClickListener {
            pokemonViewModel.showDialogInfo(requireContext())
        }
    }

    private fun initUI() {
        pokemonViewModel.getAllPokemon(provider.pagCurrentPokemon)
    }

    private fun onItemSelected(result: Result) {
        findNavController().navigate(
            AllPokemonFragmentDirections.actionAllPokemonFragmentToCurrentPokemonFragment(result.url)
        )
    }


}