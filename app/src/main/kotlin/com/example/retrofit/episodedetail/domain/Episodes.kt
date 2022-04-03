package com.example.retrofit.episodedetail.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Episodes(
    val id: Int,
    val name: String,
    val season: Int,
    val number: Int,
    val image: Image?,
    val summary: String?,
    val runtime: Int,
) : Parcelable
@Parcelize
data class Image(
    val medium: String,
    val original: String?
): Parcelable