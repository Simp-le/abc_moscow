package com.abc.moscow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import com.abc.moscow.ui.MoscowApp
import com.abc.moscow.ui.theme.MoscowTheme

/**
 * Activity for Moscow app
 */
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowWidthSize = calculateWindowSizeClass(this).widthSizeClass

            MoscowTheme {
                MoscowApp(windowWidthSize)
            }
        }
    }
}


/*
 * This app should:
 *
 * Contain multiple screens; for example, each screen can display a different category of recommendations.
 * Use the Jetpack Navigation component to enable users to navigate through your app.
 * Maintain a clear distinction between the UI layer and the data layer.
 * Use a ViewModel and make updates to the UI from the view model using the unidirectional data flow (UDF) pattern.
 * Use adaptive layouts that account for all different screen sizes.
 * Follow Material Design guidelines for adaptive design and navigation.
 */