package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.MovieAdapter
import com.example.movieapp.data.MovieData
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.listener.MovieListener

class HomeFragment : Fragment(), MovieListener {
    private lateinit var binding: FragmentHomeBinding
    private val movieList = ArrayList<MovieData>()
    private lateinit var movieAdapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addToData()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setHasFixedSize(true)
        movieAdapter = MovieAdapter(movieList, this)
        binding.recyclerView.adapter = movieAdapter

    }

    private fun addToData() {
        movieList.add(
            MovieData(
                R.drawable.black_adam,
                "Black Adam",
                "Nearly 5,000 years after he was bestowed with the almighty powers of the ancient gods--and imprisoned just as quickly--Black Adam (Dwayne Johnson) is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                "160k",
                "1.8k"
            )
        )
        movieList.add(
            MovieData(
                R.drawable.avatar,
                "Avatar",
                "James Cameron's Academy Award®-winning 2009 epic adventure \"Avatar\", returns to theaters September 23 in stunning 4K High Dynamic Range. On the lush alien world of Pandora live the Na'vi, beings who appear primitive but are highly evolved. Because the planet's environment is poisonous, human/Na'vi hybrids, called Avatars, must link to human minds to allow for free movement on Pandora.",
                "200k",
                "1.2k"
            )
        )
        movieList.add(
            MovieData(
                R.drawable.smile,
                "Smile",
                "After witnessing a bizarre, traumatic incident involving a patient, Dr. Rose Cotter (Sosie Bacon) starts experiencing frightening occurrences that she can't explain. As an overwhelming terror begins taking over her life, Rose must confront her troubling past in order to survive and escape her horrifying new reality.",
                "90k",
                "820"
            )
        )
        movieList.add(
            MovieData(
                R.drawable.eternals,
                "Eternals",
                "Marvel Studios' Eternals features an exciting new team of Super Heroes in the Marvel Cinematic Universe, ancient aliens who have been living on Earth in secret for thousands of years. Following the events of Avengers: Endgame, an unexpected tragedy forces them out of the shadows to reunite against mankind's most ancient enemy, the Deviants.",
                "120k",
                "1.3k"
            )
        )
        movieList.add(
            MovieData(
                R.drawable.bullet_train,
                "Bullet Train",
                "In Bullet Train, Brad Pitt stars as Ladybug, an unlucky assassin determined to do his job peacefully after one too many gigs gone off the rails. Fate, however, may have other plans, as Ladybug's latest mission puts him on a collision course with lethal adversaries from around the globe -- all with connected, yet conflicting, objectives -- on the world's fastest train.",
                "200k",
                "1.1k"
            )
        )
    }


    //    }
    override fun itemClick(movie_items: MovieData) {
        findNavController().navigate(
            R.id.movieDetailFragment, bundleOf(
                movie_item to movie_items
            )
        )
    }

}