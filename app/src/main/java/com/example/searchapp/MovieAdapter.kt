package com.example.searchapp

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.regex.Pattern


class MovieAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return MovieViewHolder(view)
    }

    private var highlightQuery: String = "" //searched text highlighting
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie, highlightQuery)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(newMovies: List<Movie>, query: String) {
        movies = newMovies
        highlightQuery = query
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val releaseDateTextView: TextView = itemView.findViewById(R.id.releaseDateTextView)
        private val overviewTextView: TextView = itemView.findViewById(R.id.overviewTextView)

        fun bind(movie: Movie, query: String) {
            titleTextView.text = highlightText(movie.title, query)
            releaseDateTextView.text = "Release Date: ${movie.releaseDate}"
            overviewTextView.text = highlightText(movie.overview, query)
        }
    }

    private fun highlightText(text: String, query: String): Spannable {
        val spannable = SpannableString(text)
        if (query.isNotEmpty()) {
            val pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(text)

            while (matcher.find()) {
                spannable.setSpan(
                    ForegroundColorSpan(Color.MAGENTA),
                    matcher.start(),
                    matcher.end(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spannable

    }
}