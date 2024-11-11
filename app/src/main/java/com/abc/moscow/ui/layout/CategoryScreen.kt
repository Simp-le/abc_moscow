package com.abc.moscow.ui.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abc.moscow.R
import com.abc.moscow.data.LocalPlacesDataProvider
import com.abc.moscow.model.Category
import com.abc.moscow.ui.theme.MoscowTheme
import com.abc.moscow.utils.ContentType

@Composable
fun CategoryScreen(
    windowSize: WindowWidthSizeClass,
    categories: List<Category>,
    onCategoryClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> ContentType.ListOnly
        WindowWidthSizeClass.Expanded -> ContentType.GridOnly
        else -> ContentType.ListOnly
    }

    val categoryItem: @Composable (category: Category) -> Unit = { category ->
        CategoryItem(
            category,
            onItemClick = { onCategoryClick(category) },
            modifier = Modifier.padding(
                vertical = dimensionResource(R.dimen.padding_small),
                horizontal = dimensionResource(R.dimen.padding_medium)
            )
        )
    }

    Scaffold(
        topBar = { CategoryAppBar(titleResourceId = R.string.category_list) },
        modifier = modifier
    ) { innerPadding ->
        if (contentType == ContentType.ListOnly) {
            LazyColumn(
                modifier = Modifier.padding(innerPadding),
                contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small))
            ) {
                items(categories) { category ->
                    categoryItem(category)
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(300.dp),
                modifier = Modifier.padding(innerPadding)
            ) {
                items(categories) { category ->
                    categoryItem(category)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(topStart = dimensionResource(R.dimen.card_corner_radius)),
        border = if (category == Category.ALL) BorderStroke(
            width = 0.5.dp,
            color = MaterialTheme.colorScheme.onBackground
        ) else null,
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        onClick = { onItemClick(category) }
    ) {
        Column(
            modifier = Modifier
                .height(dimensionResource(R.dimen.category_card_height))
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(category.imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(Color.DarkGray, blendMode = BlendMode.Softlight),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            )
            Text(
                stringResource(category.titleResourceId),
                modifier = Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_small)),
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryAppBar(
    titleResourceId: Int,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(titleResourceId), style = MaterialTheme.typography.displaySmall)
        },
        modifier = modifier
    )
}


@Preview
@Composable
fun PreviewCategoryItem() {
    val category = LocalPlacesDataProvider.categories[1]
    MoscowTheme {
        CategoryItem(category = category, onItemClick = { _ -> })
    }
}

@Preview(device = Devices.FOLDABLE)
@Composable
fun PreviewCategoryScreen() {
    MoscowTheme {
        CategoryScreen(
            windowSize = WindowWidthSizeClass.Expanded,
            onCategoryClick = {},
            categories = LocalPlacesDataProvider.categories
        )
    }
}