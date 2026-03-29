package org.example.project

import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.navigation.AppNav

fun MainViewController() = ComposeUIViewController {
    fun MainViewController() = ComposeUIViewController {
        val container = ApplicationContainer()
        AppNav(
            countryRepository = container.countryRepository,
            navCoordinator = container.navCoordinator
        )
    }
}