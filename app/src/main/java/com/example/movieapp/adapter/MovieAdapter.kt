package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.data.MovieData
import com.example.movieapp.R
import com.example.movieapp.fragments.HomeFragment

class MovieAdapter(private val movies: ArrayList<MovieData>, private val listener: HomeFragment) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    class MovieViewHolder(movieView : View) : RecyclerView.ViewHolder(movieView){
        val image : ImageView = movieView.findViewById(R.id.image_view)
        val title : TextView = movieView.findViewById(R.id.title_view)
        val desc : TextView = movieView.findViewById(R.id.desc_view)
        val favourite : TextView = movieView.findViewById(R.id.fav_view)
        val comment : TextView = movieView.findViewById(R.id.comment_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val movie = LayoutInflater.from(parent.context).inflate(R.layout.movie_items,parent,false)
        return MovieViewHolder(movie)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieItems = movies[position]
        holder.image.setImageResource(movieItems.image)
        holder.title.text = movieItems.title
        holder.desc.text = movieItems.desc
        holder.favourite.text = movieItems.favourite
        holder.comment.text = movieItems.comment
        holder.itemView.setOnClickListener{
            listener.itemClick(movieItems)
        }
    }
    override fun getItemCount(): Int {
        return movies.size
    }

}