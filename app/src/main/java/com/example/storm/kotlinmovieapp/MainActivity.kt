package com.example.storm.kotlinmovieapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        loadData()
    }

    private fun movieItemClicked(movie: MovieModel) {

        val intent = Intent(this@MainActivity, DetailsActivity::class.java)


        intent.putExtra("Mymovie", movie)
        startActivity(intent)

    }

    fun loadData() {

        var retrofit=Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        var service :GetMovies =retrofit.create(GetMovies::class.java)
        var call :Call<MovieArray> =service.getMovies()
        call.enqueue(object :Callback<MovieArray>{
            override fun onFailure(call: Call<MovieArray>, t: Throwable) {
                println("Error")
            }

            override fun onResponse(call: Call<MovieArray>, response: Response<MovieArray>) {
                var movieList = response.body()?.athletes!!


                val rAdapter = movieAdapter(this@MainActivity,movieList , { movie : MovieModel -> movieItemClicked(movie) })

                recyclView.adapter = rAdapter;
                  }

        })
    }
}
