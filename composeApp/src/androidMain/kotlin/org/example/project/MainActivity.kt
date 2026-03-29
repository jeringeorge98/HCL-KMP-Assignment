package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.navigation.AppNav

class MainActivity : ComponentActivity() {
    lateinit var container: ApplicationContainer
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge(
//            SystemBarStyle.light(
//                Color(0xFF06969A).toArgb(),
//                // Deep Sage
//                darkScrim  = Color(0xFF0D2D2D).toArgb()
//            )
//        )
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        container = ApplicationContainer()
        setContent {
            MaterialTheme {
                AppNav(
                    countryRepository = container.countryRepository,
                    navCoordinator = container.navCoordinator
                )
            }

        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}