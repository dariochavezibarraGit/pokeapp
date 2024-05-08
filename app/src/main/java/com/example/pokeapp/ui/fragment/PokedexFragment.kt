package com.example.pokeapp.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokeapp.R
import com.example.pokeapp.data.remote.client.PokeApiServiceGenerator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PokedexFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class PokedexFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var pokeApiServiceGenerator: PokeApiServiceGenerator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pokedex, container, false)

        pokeApiServiceGenerator = PokeApiServiceGenerator()
        pokeApiServiceGenerator.getPokemonById(2)

        pokeApiServiceGenerator.pokemonSpriteUrl.observe(viewLifecycleOwner) { url ->
            val imageView = view.findViewById<ImageView>(R.id.pokemonImage)
            Glide.with(this).load(url).into(imageView)
        }

        pokeApiServiceGenerator.pokemonInfo.observe(viewLifecycleOwner) { pokemon ->
            val nameTextView = view.findViewById<TextView>(R.id.pokemonName)
            nameTextView.text = pokemon.name
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Pokedex.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PokedexFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}