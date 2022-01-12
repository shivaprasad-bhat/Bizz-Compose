package me.svbneelmne.compose.bizzzz

import androidx.annotation.DrawableRes

data class ProfileData(
    val name: String,
    val url: String,
    @DrawableRes val imageAsset: Int
)

