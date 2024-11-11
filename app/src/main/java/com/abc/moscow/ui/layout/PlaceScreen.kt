package com.abc.moscow.ui.layout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abc.moscow.R
import com.abc.moscow.data.LocalPlacesDataProvider
import com.abc.moscow.model.Category
import com.abc.moscow.model.Place
import com.abc.moscow.ui.theme.MoscowTheme
import com.abc.moscow.utils.ContentType

@Composable
fun PlaceScreen(
    windowSize: WindowWidthSizeClass,
    isShowingListPage: Boolean,
    category: Category,
    places: List<Place>,
    selectedPlace: Place,
    onPlaceClick: (Place) -> Unit,
    onBackButtonClick: () -> Unit,
    onPlaceScreenBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact, WindowWidthSizeClass.Medium -> ContentType.ListOnly
        WindowWidthSizeClass.Expanded -> ContentType.ListAndDetail
        else -> ContentType.ListOnly
    }

    if (contentType == ContentType.ListAndDetail) PlaceListDetail(
        category = category,
        places = places,
        selectedPlace = selectedPlace,
        onItemClick = onPlaceClick,
        onBackButtonClick = onBackButtonClick,
        modifier = modifier
    )
    else {
        if (isShowingListPage)
            PlaceList(
                category = category,
                places = places,
                onItemClick = { onPlaceClick(it) },
                onBackButtonClick = onBackButtonClick,
                modifier = modifier
            )
        else PlaceDetail(
            place = selectedPlace,
            windowSize = windowSize,
            modifier = modifier,
            onBackButtonClick = onPlaceScreenBackButtonClick
        )
    }
}

@Composable
fun PlaceListDetail(
    category: Category,
    places: List<Place>,
    selectedPlace: Place,
    onItemClick: (Place) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier) {
        PlaceList(
            category = category,
            places = places,
            onItemClick = onItemClick,
            onBackButtonClick = onBackButtonClick,
            modifier = Modifier.weight(2f)
        )
        PlaceDetail(
            place = selectedPlace,
            windowSize = WindowWidthSizeClass.Expanded,
            modifier = Modifier.weight(3f)
        )
    }
}

@Composable
fun PlaceList(
    category: Category,
    places: List<Place>,
    onItemClick: (Place) -> Unit,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(topBar = {
        CategoryAppBar(
            titleResourceId = category.titleResourceId,
            onBackButtonClick = onBackButtonClick
        )
    }, modifier = modifier) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(places) { place ->
                PlaceItem(
                    place = place,
                    onItemClick = onItemClick,
                    modifier = Modifier.padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun PlaceDetail(
    place: Place,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    onBackButtonClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            if (windowSize != WindowWidthSizeClass.Expanded)
                PlaceAppBar(
                    titleResourceId = place.titleResourceId,
                    onBackButtonClick = onBackButtonClick
                )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
                .verticalScroll(rememberScrollState())
        ) {
            if (windowSize == WindowWidthSizeClass.Expanded) Text(
                stringResource(place.titleResourceId),
                style = MaterialTheme.typography.displaySmall,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_medium))
                    .fillMaxWidth()
            ) else Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
            Box {
                Image(
                    painter = painterResource(place.imageResourceId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 30.dp,
                                bottomStart = 30.dp,
                                bottomEnd = 4.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )
                if (place.isPopular)
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = stringResource(R.string.popular),
                        modifier = Modifier
                            .padding(dimensionResource(R.dimen.padding_small))
                            .align(Alignment.BottomEnd)
                            .size(35.dp),
                        tint = MaterialTheme.colorScheme.tertiaryContainer
                    )
            }
            Text(
                stringResource(place.addressResourceId),
                style = MaterialTheme.typography.titleMedium,
                lineHeight = 18.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                stringResource(R.string.description),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                stringResource(place.descriptionResourceId),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun PlaceItem(place: Place, onItemClick: (Place) -> Unit, modifier: Modifier = Modifier) {
    val shape = RoundedCornerShape(topStart = dimensionResource(R.dimen.card_corner_radius))
    Card(
        modifier = modifier,
        shape = shape,
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.onBackground),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        elevation = CardDefaults.elevatedCardElevation(),
        onClick = { onItemClick(place) }
    ) {
        Column(
            modifier = Modifier
                .height(dimensionResource(R.dimen.category_card_height))
                .fillMaxWidth()
        ) {
            Image(
                painterResource(place.imageResourceId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_medium))
            ) {
                Text(
                    stringResource(place.titleResourceId),
                    softWrap = true,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    stringResource(place.addressResourceId),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryAppBar(
    titleResourceId: Int,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(stringResource(titleResourceId), style = MaterialTheme.typography.displaySmall)
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        modifier = modifier.padding(end = dimensionResource(R.dimen.padding_medium))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceAppBar(
    titleResourceId: Int,
    onBackButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LargeTopAppBar(
        title = {
                Text(
                stringResource(titleResourceId),
                style = MaterialTheme.typography.displaySmall
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackButtonClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(scrolledContainerColor = TopAppBarDefaults.largeTopAppBarColors().containerColor),
        modifier = modifier.padding(end = dimensionResource(R.dimen.padding_medium))
    )
}



@Preview(showBackground = true, device = Devices.TABLET)
@Composable
fun PreviewPlaceListDetail() {
    MoscowTheme {
        PlaceListDetail(
            category = LocalPlacesDataProvider.defaultCategory,
            places = LocalPlacesDataProvider.getAllPlacesData(),
            selectedPlace = LocalPlacesDataProvider.defaultPlace,
            onItemClick = { _ -> },
            onBackButtonClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlaceList() {
    MoscowTheme {
        PlaceList(
            category = LocalPlacesDataProvider.defaultCategory,
            places = LocalPlacesDataProvider.getCategoryPlacesData(LocalPlacesDataProvider.defaultCategory),
            onItemClick = { _ -> },
            onBackButtonClick = { },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPlaceDetail() {
    MoscowTheme {
        PlaceDetail(
            place = LocalPlacesDataProvider.getAllPlacesData()[9],
            windowSize = WindowWidthSizeClass.Compact,
            onBackButtonClick = { },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview
@Composable
fun PreviewPlaceScreen() {
    MoscowTheme {
        PlaceScreen(
            windowSize = WindowWidthSizeClass.Compact,
            isShowingListPage = true,
            category = LocalPlacesDataProvider.defaultCategory,
            places = LocalPlacesDataProvider.getAllPlacesData(),
            selectedPlace = LocalPlacesDataProvider.defaultPlace,
            onPlaceClick = { _ -> },
            onBackButtonClick = {},
            onPlaceScreenBackButtonClick = {}
        )
    }
}
