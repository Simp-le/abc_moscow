package com.abc.moscow.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.abc.moscow.R

/**
 * Data model for a place
 */
data class Place(
    val id: Int,
    @StringRes val titleResourceId: Int,
    @StringRes val addressResourceId: Int,
    @StringRes val descriptionResourceId: Int,
    @DrawableRes val imageResourceId: Int,
    val isPopular: Boolean = false,
    val categories: Set<Category> = emptySet()
)

enum class Category(@StringRes val titleResourceId: Int, @DrawableRes val imageResourceId: Int) {
    HISTORICAL(R.string.historical, R.drawable.historical),
    RESTAURANTS(R.string.restaurants, R.drawable.restaurants),
    INTERESTING(R.string.interesting, R.drawable.interesting),
    ALL(R.string.all, R.drawable.all)
}
