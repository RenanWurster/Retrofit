package com.example.retrofit.shows.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofit.domain.Series
import com.example.retrofit.data.network.services
import com.example.retrofit.databinding.ActivitySeriesBinding
import com.example.retrofit.seriesdetail.presentation.SeriesDetail
import com.example.retrofit.shows.data.SeriesRepository

class SeriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeriesBinding
    private lateinit var seriesViewModel: SeriesViewModel
    private lateinit var seriesViewModelFactory: SeriesViewModel.Factory
    private val adapterSeries = SeriesAdapter(::seriesClickListener)


    private fun seriesClickListener(series: Series) {
startActivity(SeriesDetail.createIntent(this,series)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvSeries.adapter = adapterSeries

        seriesViewModelFactory = SeriesViewModel.Factory(SeriesRepository(services()))
        seriesViewModel = ViewModelProvider(this, seriesViewModelFactory)
            .get(SeriesViewModel::class.java)

        seriesViewModel.series.observe(this) { series ->
            series?.let {
                Log.d("renan", "chegando$it")
                adapterSeries.submitList(it)
            }
        }
    }
}