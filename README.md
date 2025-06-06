# NewPipe Android Client

This repository contains a simplified, modular Android application inspired by the original [NewPipe](https://newpipe.net) project. It is built entirely with **Kotlin** and **Jetpack Compose**, using **Hilt** for dependency injection.

## Features
- Browse trending streams on the home screen
- Search for videos across supported services
- Watch videos with an ExoPlayer based player
- Subscribe to channels and view a personalised feed
- Maintain a local watch history
- Manage playlists and playlist contents
- View individual channels and their uploads
- Download audio or video streams for offline playback
- Customise appearance and playback defaults in the settings

Most features rely on a local Room database and the NewPipe Extractor library. Several repository implementations are currently stubbed and meant as examples.

## Project Structure
The codebase is organised as a set of Gradle modules:

- **app** – the main Android application module
- **core:model** – data classes shared across the project
- **core:data** – Room DAOs, repositories and preferences
- **core:domain** – use cases that expose app logic
- **core:ui** – reusable Compose components and theming
- **feature:** modules for each screen such as `feed`, `player`, `search`, `subscriptions`, `history`, `playlists`, `playlist_detail`, `downloads`, `channel` and `settings`

Each feature module defines its own UI screens and ViewModels while depending on the domain layer for business logic.

## Building
1. Install the Android SDK and ensure `adb` is available.
2. Create a `local.properties` file in the project root with the path to your SDK:
   ```
   sdk.dir=/path/to/Android/sdk
   ```
3. Run the following command to produce a debug APK:
   ```bash
   ./gradlew assembleDebug
   ```
   The output can be found under `app/build/outputs/apk/`.

Unit and instrumentation tests are located under `app/src/test` and `app/src/androidTest` respectively. Running them requires a proper Android tooling setup.

## License
NewPipe is released under the terms of the [GNU General Public License v3](https://www.gnu.org/licenses/gpl-3.0.en.html).
