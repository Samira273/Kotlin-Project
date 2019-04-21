package com.example.storm.kotlinmovieapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide;
import com.example.storm.kotlinmovieapp.R.id.movie_name

class movieAdapter (val context: Context ,var movie_list :List<MovieModel> ,val clickListener: (MovieModel) -> Unit)
    : RecyclerView.Adapter<movieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        var v = LayoutInflater.from(p0?.context).inflate(R.layout.row_layout, p0, false)
        return MovieViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movie_list.size
    }

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.bind(context, movie_list[p1],clickListener)
    }


    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var moviename = itemView.findViewById<TextView>(R.id.movie_name)
        var brief = itemView.findViewById<TextView>(R.id.movie_brief)
        var img = itemView.findViewById<ImageView>(R.id.movie_image)

        fun bind(context: Context, movie: MovieModel, clickListener: (MovieModel) -> Unit) {
            moviename?.text = movie.name
            brief?.text = movie.brief
            if (movie.image != "") {
                Glide.with(context)
                        .load(movie.image)
                        .into(img);

            }else {
                img.visibility=View.GONE
                val params = moviename.getLayoutParams() as RelativeLayout.LayoutParams
                params.width = 600
                params.marginStart =60
                moviename.setLayoutParams(params)
                val params1 = brief.getLayoutParams() as RelativeLayout.LayoutParams
                params1.width = 800
                params1.marginStart =60
                brief.setLayoutParams(params1)
            }
           itemView.setOnClickListener { clickListener(movie)}
           // brief.setOnClickListener { clickListener(movie)}
        }
    }

}