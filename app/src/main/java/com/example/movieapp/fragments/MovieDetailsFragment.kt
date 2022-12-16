package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movieapp.data.MovieData
import com.example.movieapp.databinding.FragmentMovieDetailsBinding

const val movie_item = "movie_item"

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private var movie: MovieData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(movie_item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (movie != null) {
            binding.itemTitleDetailText.text = movie!!.title
            binding.itemDetailImg.setImageResource(movie!!.image)
            binding.itemDescDetailText.text = movie!!.desc
        }
    }

}