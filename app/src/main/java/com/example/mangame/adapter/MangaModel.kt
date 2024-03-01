package com.example.mangame.adapter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MangaModel(
    val title: String,
    val desc : String,
    val thumbnail: String,
    val views : String,
    val comments : String,
    val rating : String,
    val genre : String,
    val author : String,
) : Parcelable
