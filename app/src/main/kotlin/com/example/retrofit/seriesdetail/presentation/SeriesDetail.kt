package com.example.retrofit.seriesdetail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.retrofit.data.network.ApiServiceRetrofit.services
import com.example.retrofit.shows.domain.Series
import com.example.retrofit.databinding.ActivitySeriesDetailBinding
import com.example.retrofit.seriesdetail.data.SeriesDetailRepository
import com.example.retrofit.seriesdetail.domain.Seasons
import com.example.retrofit.seasons.presentation.SeasonActivity

class SeriesDetail : AppCompatActivity() {
    private lateinit var series: Series
    private lateinit var binding: ActivitySeriesDetailBinding
    private lateinit var seriesDetailViewModel: SeriesDetailViewModel
    private lateinit var seriesDetailViewModelFactory: SeriesDetailViewModel.Factory
    private val seasonsAdapter = SeasonAdapter(::seasonsClickListener)


    private fun seasonsClickListener(seasons: Seasons) {
        startActivity(SeasonActivity.createIntent1(this,seasons)) }

    companion object {
        private const val SERIES_KEY = "series_key"

        fun createIntent(context: Context, series: Series): Intent {
            val intent = Intent(context, SeriesDetail::class.java)
            intent.putExtra(SERIES_KEY, series)
            return intent

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.rvSeasons.adapter = seasonsAdapter

        series = intent.extras?.get(SERIES_KEY) as Series

        binding.imageFilmDetail.load(series.image?.original)
        {
            crossfade(true)
            transformations(RoundedCornersTransformation(15f))
        }
        binding.txtfilmDetail.text = series.summary
        binding.txtNameFilmDetail.text = series.name
        binding.txtGenresFilmDetail.text = series.genres.toString()



        seriesDetailViewModelFactory = SeriesDetailViewModel.Factory(SeriesDetailRepository(services()))
        seriesDetailViewModel = ViewModelProvider(this, seriesDetailViewModelFactory)
            .get(SeriesDetailViewModel::class.java)
        series.id?.let { seriesDetailViewModel.getSeasonsById(it) }

        seriesDetailViewModel.seasons.observe(this, Observer {
                seasons -> seasons?.let {
            Log.d("renan","chegando$it")
            seasonsAdapter.submitList(it)
        }
        })


    }
}