package com.portafoliowebmariano.pokedex.ui.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.portafoliowebmariano.pokedex.R
import com.portafoliowebmariano.pokedex.databinding.FragmentCurrentPokemonBinding
import com.portafoliowebmariano.pokedex.model.data.PokemonProvider
import com.portafoliowebmariano.pokedex.model.data.Result
import com.portafoliowebmariano.pokedex.ui.view.adapter.AbilitiesAdapter
import com.portafoliowebmariano.pokedex.ui.view.adapter.TypeAdapter
import com.portafoliowebmariano.pokedex.viewmodel.PokemonViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class CurrentPokemonFragment : Fragment() {

    private lateinit var binding: FragmentCurrentPokemonBinding
    private lateinit var typeAdapter: TypeAdapter
    private lateinit var abilityAdapter: AbilitiesAdapter

    private val pokemonViewModel: PokemonViewModel by viewModels()
    private val args: CurrentPokemonFragmentArgs by navArgs()

    private val provider = PokemonProvider
    val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    val manager2 = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentPokemonBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            initUI()
            observer()
            controller()

        }
    }

    private fun initAdapter() {
        //Adapter Type
        typeAdapter = TypeAdapter(provider.currentPokemon?.types ?: emptyList())
        binding.rvType.layoutManager = manager
        binding.rvType.adapter = typeAdapter
        //Adapter Ability

        abilityAdapter = AbilitiesAdapter(provider.currentPokemon?.abilities ?: emptyList())
        binding.rvAbilities.layoutManager = manager2
        binding.rvAbilities.adapter = abilityAdapter
    }

    private fun controller() {
        clickButtonBack()
        clickButtonLike()
    }

    private fun clickButtonLike() {
        binding.ivLike.setOnClickListener {
            val pokemon: String? = provider.currentPokemon!!.name
            toast(pokemonViewModel.setFavoritesPokemon())
        }
    }

    private fun clickButtonBack() {
        binding.ivButtonBack.setOnClickListener {
            findNavController().navigate(CurrentPokemonFragmentDirections.actionCurrentPokemonFragmentToAllPokemonFragment())
        }

    }

    private fun observer() {
        getCurrentPokemon()
        observerProgressBar()
        observerColor()
        observerBgLoading()
    }


    private fun observerBgLoading() {
        pokemonViewModel.bgLoading.observe(viewLifecycleOwner) {
            binding.cvLoading.isVisible = it
        }
    }

    private fun observerColor() {
        pokemonViewModel.colorPokemon.observe(viewLifecycleOwner) {
            selectColor(it)
        }
    }


    private fun observerProgressBar() {
        pokemonViewModel.isLoading.observe(viewLifecycleOwner) {
            binding.pbPokemon.isVisible = it
        }
    }

    private fun getCurrentPokemon() {
        pokemonViewModel.currentPokemon.observe(viewLifecycleOwner) { pokemon ->

            if (pokemon != null) {
                binding.tvNamePokemon.text = pokemon.name
                Picasso.get().load(pokemon.sprites.front_default).into(binding.ivImagePokemon)
                binding.tvId.text = "# ${pokemon.id}"

                binding.cvHpLevelEmpty.layoutParams.width = dpToPixels(255)

                val Listtipos = mutableListOf<String>()
                val listAbilities = mutableListOf<String>()

                for (tipos in pokemon.types) {
                    Listtipos.add(tipos.type.name)
                }
                for (ability in pokemon.abilities) {
                    listAbilities.add(ability.ability.name)
                }

                binding.tvHpLevel.text = formatNumber(pokemon.stats[0].base_stat)
                binding.cvHpLevelEmpty.layoutParams.width = dpToPixels(pokemon.stats[0].base_stat)

                binding.tvAtkLevel.text = formatNumber(pokemon.stats[1].base_stat)
                binding.cvAtkLevelEmpty.layoutParams.width = dpToPixels(pokemon.stats[1].base_stat)

                binding.tvDefLevel.text = formatNumber(pokemon.stats[2].base_stat)
                binding.cvDefLevelEmpty.layoutParams.width = dpToPixels(pokemon.stats[2].base_stat)

                binding.tvSatkLevel.text = formatNumber(pokemon.stats[3].base_stat)
                binding.cvSatkLevelEmpty.layoutParams.width = dpToPixels(pokemon.stats[3].base_stat)

                binding.tvSdefLevel.text = formatNumber(pokemon.stats[4].base_stat)
                binding.cvSdefLevelEmpty.layoutParams.width = dpToPixels(pokemon.stats[4].base_stat)

                binding.tvSpdLevel.text = formatNumber(pokemon.stats[5].base_stat)
                binding.cvSpdLevelEmpty.layoutParams.width = dpToPixels(pokemon.stats[5].base_stat)


                for (pokemonFav in provider.favoritesPokemon) {

                    if (pokemonFav.name == pokemon.name) {
                        binding.ivLike.setImageResource(R.drawable.corazon2)

                    } else {
                        binding.ivLike.setImageResource(R.drawable.corazon1)
                    }
                }
                initAdapter()
            }
            else{
                pokemonViewModel.showDialogError(requireContext(),"No se encontro el pokemon revise su conexion a internet")
            }
        }
    }

    private fun initUI() {
        val url = args.pokemonUrl
        val codigoColor: String = extractNumber(url).toString()

        getColor(codigoColor)
        pokemonViewModel.getCurrentPokemon(url)
    }

    fun dpToPixels(dp: Int): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    fun formatNumber(num: Int): String {
        // Usar String.format para formatear el número con ceros adicionales según sea necesario
        return String.format("%03d", num)
    }

    fun extractNumber(url: String): Int? {
        val regex = Regex("/(\\d+)/$")
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value?.toInt()
    }

    fun getColor(query: String): String {
        return pokemonViewModel.getColorPokemon(query).toString()
    }

    fun selectColor(color: String) {
        when (color) {
            "red" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorRed
                )
            )

            "blue" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorBlue
                )
            )

            "green" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorGreen
                )
            )

            "black" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorBlack
                )
            )

            "brown" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorBrown
                )
            )

            "gray" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorGray
                )
            )

            "pink" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPink
                )
            )

            "purple" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPurple
                )
            )

            "white" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorWhite
                )
            )

            "yellow" -> binding.clFon.setBackgroundColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorYellow
                )
            )

            return -> ""
        }
    }

    fun toast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}