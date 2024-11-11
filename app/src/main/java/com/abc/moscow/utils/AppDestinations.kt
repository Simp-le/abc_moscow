package com.abc.moscow.utils

import androidx.annotation.StringRes
import com.abc.moscow.R

enum class AppDestinations(@StringRes val title: Int) {
    CATEGORY(title = R.string.category_list),
    PLACE(title = R.string.place_list)
}