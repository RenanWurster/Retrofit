package com.example.retrofit.shows.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.retrofit.R
import com.example.retrofit.domain.Series
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SeriesAdapter(private val callback: (Series) -> Unit) :
    ListAdapter<Series, SeriesAdapter.SeriesViewHolder>(SeriesAdapter) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_serie, parent, false)
        return SeriesViewHolder(view)
    }

    @ExperimentalCoroutinesApi
    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        holder.bind(getItem(position), callback)
    }

    class SeriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @ExperimentalCoroutinesApi
        fun bind(data: Series, callback: (Series) -> Unit) {
            with(itemView) {

                val ivSeries = findViewById<ImageView>(R.id.ivSerie)
                val txtSeries = findViewById<TextView>(R.id.txtSerie)
                txtSeries.text = data.name

                ivSeries.setOnClickListener {
                    callback.invoke(data)
                }

                ivSeries.load(data.image.original) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(15f))
                }
            }
        }
    }

    companion object : DiffUtil.ItemCallback<Series>() {
        override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
            return oldItem == newItem
        }

    }
}