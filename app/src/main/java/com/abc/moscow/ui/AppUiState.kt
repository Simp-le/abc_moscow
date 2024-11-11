package com.abc.moscow.ui

import com.abc.moscow.model.Category
import com.abc.moscow.model.Place

data class AppUiState(
    val isShowingListPage: Boolean,
    val categories: List<Category>,
    val currentCategory: Category,
    val currentPlaceList: List<Place>,
    val currentPlace: Place
)
