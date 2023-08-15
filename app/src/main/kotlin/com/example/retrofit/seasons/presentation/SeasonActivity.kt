package com.example.retrofit.seasons.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.retrofit.data.network.services
import com.example.retrofit.databinding.ActivitySeasonsBinding
import com.example.retrofit.domain.Series
import com.example.retrofit.episodedetail.data.EpisodesRepository
import com.example.retrofit.episodedetail.domain.Episodes
import com.example.retrofit.episodedetail.presentation.EpisodesDetail
import com.example.retrofit.seriesdetail.domain.Seasons


class SeasonActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySeasonsBinding
    private lateinit var seasons: Seasons
    private lateinit var episodes: Episodes
    private lateinit var serie: Series
    private val apiService = services()
    private val repository = EpisodesRepository(apiService)
    private val seasonViewModelFactory = SeasonViewModel.Factory(repository)
    private lateinit var seasonViewModel: SeasonViewModel
    private  var episodesAdapter = EpisodesAdapter(::episodesClickListener)


    private fun episodesClickListener(episodes: Episodes) {
        startActivity(EpisodesDetail.createIntent2(this,episodes)) }

    companion object {
        private const val SEASONS_KEY = "seasons_key"

        fun createIntent1(context: Context, seasons: Seasons): Intent {
            val intent = Intent(context, SeasonActivity::class.java)
            intent.putExtra(SEASONS_KEY, seasons)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeasonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        seasons = intent.extras?.get(SEASONS_KEY) as Seasons
        binding.rvEpisodes.adapter = episodesAdapter

        binding.txtPremiereDate.text = seasons.episodeOrder.toString()

        binding.imageSeason.load(seasons.image?.original)
        {
            crossfade(true)
            transformations(RoundedCornersTransformation(15f))
        }

        seasonViewModel = ViewModelProvider(this, seasonViewModelFactory)
            .get(SeasonViewModel::class.java)


        seasons.id?.let { seasonViewModel.getEpisodesById(it) }
        seasonViewModel.episodes.observe(this, Observer {
            episodes -> episodes?.let {
                episodesAdapter.submitList(it)
        }
        })


    }
}