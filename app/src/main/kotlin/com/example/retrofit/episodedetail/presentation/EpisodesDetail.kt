package com.example.retrofit.episodedetail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.retrofit.databinding.ActivityEpisodesDetailBinding
import com.example.retrofit.episodedetail.domain.Episodes
import com.example.retrofit.seasons.presentation.SeasonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodesDetail : AppCompatActivity() {

    //private lateinit var seasonViewModelFactory: SeasonViewModelFactory
    private val seasonViewModel: SeasonViewModel by viewModels()
    private lateinit var episodes: Episodes
    private lateinit var binding: ActivityEpisodesDetailBinding

    companion object {
        private const val EPISODES_KEY = "episodes_key"

        fun createIntent2(context: Context, episodes: Episodes): Intent {
            val intent = Intent(context, EpisodesDetail::class.java)
            intent.putExtra(EPISODES_KEY, episodes)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEpisodesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        episodes = intent.extras?.get(EPISODES_KEY) as Episodes

        binding.imageEpisodeDetail.load(episodes.image?.original)
        binding.txtEpisodeDetail.text = episodes.summary
        binding.txtGenresRunTimeEpisode.text = episodes.runtime.toString()
        binding.txtNameEpisodeDetail.text = episodes.name
    }
}