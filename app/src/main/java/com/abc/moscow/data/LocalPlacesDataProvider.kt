package com.abc.moscow.data

import com.abc.moscow.R
import com.abc.moscow.model.Category
import com.abc.moscow.model.Place
import kotlin.enums.EnumEntries

object LocalPlacesDataProvider {

    val categories: EnumEntries<Category> = Category.entries
    val defaultCategory: Category = categories.first()

    val defaultPlace = getAllPlacesData().first { it.categories.contains(defaultCategory) }

    fun getAllPlacesData(): List<Place> {
        return listOf(
            Place(
                id = 1,
                titleResourceId = R.string.place_name_kremlin,
                addressResourceId = R.string.place_address_kremlin,
                descriptionResourceId = R.string.place_description_kremlin,
                imageResourceId = R.drawable.kremlin,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 2,
                titleResourceId = R.string.place_name_gum,
                addressResourceId = R.string.place_address_gum,
                descriptionResourceId = R.string.place_description_gum,
                imageResourceId = R.drawable.gum,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 3,
                titleResourceId = R.string.place_name_tretyakov_gallery,
                addressResourceId = R.string.place_address_tretyakov_gallery,
                descriptionResourceId = R.string.place_description_tretyakov_gallery,
                imageResourceId = R.drawable.tretyakov_gallery,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 4,
                titleResourceId = R.string.place_name_red_square,
                addressResourceId = R.string.place_address_red_square,
                descriptionResourceId = R.string.place_description_red_square,
                imageResourceId = R.drawable.red_square,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 5,
                titleResourceId = R.string.place_name_pushkin_state_museum_of_fine_arts,
                addressResourceId = R.string.place_address_pushkin_state_museum_of_fine_arts,
                descriptionResourceId = R.string.place_description_pushkin_state_museum_of_fine_arts,
                imageResourceId = R.drawable.pushkin_state_museum_of_fine_arts,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 6,
                titleResourceId = R.string.place_name_cathedral_of_christ_the_saviour,
                addressResourceId = R.string.place_address_cathedral_of_christ_the_saviour,
                descriptionResourceId = R.string.place_description_cathedral_of_christ_the_saviour,
                imageResourceId = R.drawable.cathedral_of_christ_the_saviour,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 7,
                titleResourceId = R.string.place_name_bolshoi_theatre,
                addressResourceId = R.string.place_address_bolshoi_theatre,
                descriptionResourceId = R.string.place_description_bolshoi_theatre,
                imageResourceId = R.drawable.bolshoi_theatre,
                isPopular = true,
                categories = setOf(Category.HISTORICAL)
            ),
            Place(
                id = 8,
                titleResourceId = R.string.place_name_vdnkh,
                addressResourceId = R.string.place_address_vdnkh,
                descriptionResourceId = R.string.place_description_vdnkh,
                imageResourceId = R.drawable.vdnkh,
                isPopular = true,
                categories = setOf(Category.INTERESTING, Category.HISTORICAL)
            ),
            Place(
                id = 9,
                titleResourceId = R.string.place_name_central_market,
                addressResourceId = R.string.place_address_central_market,
                descriptionResourceId = R.string.place_description_central_market,
                imageResourceId = R.drawable.central_market,
                isPopular = true,
                categories = setOf(Category.RESTAURANTS)
            ),
            Place(
                id = 10,
                titleResourceId = R.string.place_name_cafe_at_the_garage_museum,
                addressResourceId = R.string.place_address_cafe_at_the_garage_museum,
                descriptionResourceId = R.string.place_description_cafe_at_the_garage_museum,
                imageResourceId = R.drawable.cafe_at_the_garage_museum,
                isPopular = true,
                categories = setOf(Category.RESTAURANTS)
            ),
            Place(
                id = 11,
                titleResourceId = R.string.place_name_kofemania,
                addressResourceId = R.string.place_address_kofemania,
                descriptionResourceId = R.string.place_description_kofemania,
                imageResourceId = R.drawable.kofemania,
                isPopular = true,
                categories = setOf(Category.RESTAURANTS)
            ),
            Place(
                id = 12,
                titleResourceId = R.string.place_name_briquette_market,
                addressResourceId = R.string.place_address_briquette_market,
                descriptionResourceId = R.string.place_description_briquette_market,
                imageResourceId = R.drawable.briquette_market,
                isPopular = false,
                categories = setOf(Category.RESTAURANTS)
            ),
            Place(
                id = 13,
                titleResourceId = R.string.place_name_khlebzavod,
                addressResourceId = R.string.place_address_khlebzavod,
                descriptionResourceId = R.string.place_description_khlebzavod,
                imageResourceId = R.drawable.khlebzavod,
                isPopular = true,
                categories = setOf(Category.RESTAURANTS, Category.HISTORICAL)
            ),
            Place(
                id = 14,
                titleResourceId = R.string.place_name_aptekarski_ogorod,
                addressResourceId = R.string.place_address_aptekarski_ogorod,
                descriptionResourceId = R.string.place_description_aptekarski_ogorod,
                imageResourceId = R.drawable.aptekarski_ogorod,
                isPopular = false,
                categories = setOf(Category.INTERESTING, Category.HISTORICAL)
            ),
            Place(
                id = 15,
                titleResourceId = R.string.place_name_rooftop_at_moscow_city,
                addressResourceId = R.string.place_address_rooftop_at_moscow_city,
                descriptionResourceId = R.string.place_description_rooftop_at_moscow_city,
                imageResourceId = R.drawable.rooftop_at_moscow_city,
                isPopular = false,
                categories = setOf(Category.INTERESTING)
            ),
            Place(
                id = 16,
                titleResourceId = R.string.place_name_dizayn_zavod,
                addressResourceId = R.string.place_address_dizayn_zavod,
                descriptionResourceId = R.string.place_description_dizayn_zavod,
                imageResourceId = R.drawable.dizayn_zavod,
                isPopular = true,
                categories = setOf(Category.INTERESTING, Category.HISTORICAL)
            ),
            Place(
                id = 17,
                titleResourceId = R.string.place_name_artplay,
                addressResourceId = R.string.place_address_artplay,
                descriptionResourceId = R.string.place_description_artplay,
                imageResourceId = R.drawable.artplay,
                isPopular = false,
                categories = setOf(Category.INTERESTING)
            ),
            Place(
                id = 18,
                titleResourceId = R.string.place_name_vinzavod,
                addressResourceId = R.string.place_address_vinzavod,
                descriptionResourceId = R.string.place_description_vinzavod,
                imageResourceId = R.drawable.vinzavod,
                isPopular = false,
                categories = setOf(Category.INTERESTING, Category.HISTORICAL)
            ),
            Place(
                id = 19,
                titleResourceId = R.string.place_name_new_tretyakov_gallery,
                addressResourceId = R.string.place_address_new_tretyakov_gallery,
                descriptionResourceId = R.string.place_description_new_tretyakov_gallery,
                imageResourceId = R.drawable.new_tretyakov_gallery,
                isPopular = true,
                categories = setOf(Category.INTERESTING, Category.HISTORICAL)
            ),
        )
    }

    fun getCategoryPlacesData(category: Category): List<Place> {
        return if (category == Category.ALL)
            getAllPlacesData()
        else
            getAllPlacesData().filter { it.categories.contains(category) }
    }
}