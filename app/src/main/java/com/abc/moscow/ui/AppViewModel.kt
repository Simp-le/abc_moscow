package com.abc.moscow.ui

import androidx.lifecycle.ViewModel
import com.abc.moscow.data.LocalPlacesDataProvider
import com.abc.moscow.model.Category
import com.abc.moscow.model.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        AppUiState(
            isShowingListPage = true,
            categories = LocalPlacesDataProvider.categories,
            currentCategory = LocalPlacesDataProvider.defaultCategory,
            currentPlaceList = LocalPlacesDataProvider.getAllPlacesData()
                .filter { it.categories.contains(LocalPlacesDataProvider.defaultCategory) },
            currentPlace = LocalPlacesDataProvider.defaultPlace
        )
    )

    val uiState = _uiState.asStateFlow()

    fun setCategory(category: Category) {
        _uiState.update {
            val placeList = LocalPlacesDataProvider.getCategoryPlacesData(category)
            it.copy(
                currentCategory = category,
                currentPlaceList = placeList,
                currentPlace = placeList.first()
            )
        }
    }

    fun setPlace(place: Place) {
        _uiState.update {
            it.copy(isShowingListPage = false, currentPlace = place)
        }
    }

    fun placeScreenBackButtonClick() {
        _uiState.update { it.copy(isShowingListPage = true) }
    }
}