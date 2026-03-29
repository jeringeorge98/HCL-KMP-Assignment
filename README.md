This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## App Description

This application is a simple Country Information app built using Kotlin Multiplatform Mobile (KMM) and Compose Multiplatform. It allows users to browse a list of countries, search for specific countries, and view detailed information about each country.

### Architecture

The app follows the MVVM (Model-View-ViewModel) architectural pattern to ensure a clear separation of concerns, testability, and maintainability. It also utilizes the Repository pattern to abstract data sources and provide a clean API for data access.

### Technologies Used

*   **Kotlin Multiplatform Mobile (KMM):** For sharing code between Android and iOS.
*   **Compose Multiplatform:** For building declarative UI across platforms.
*   **Ktor Client:** For making network requests to fetch country data.
*   **Kotlinx.serialization:** For efficient JSON serialization and deserialization.
*   **Dependency Injection:** To manage dependencies and improve testability.

### Screens

*   **Home Screen:** Displays a scrollable list of all countries. It includes a search bar at the top, allowing users to filter countries by name. Tapping on a country in the list navigates to the detail screen.
*   **Detail Screen:** Shows comprehensive information about a selected country, such as its capital, population, region, and currency.

### Data Displayed from Country Detail Response

The app displays the following key information from the `CountryDetail` response for each country:

*   **Official Name:** The full official name of the country.
*   **Capital:** The capital city of the country.
*   **Population:** The total population of the country.
*   **Region:** The geographical region to which the country belongs.
*   **Currencies:** A list of currencies used in the country.
*   **Languages:** A list of languages spoken in the country.
