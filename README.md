# NewPipe

NewPipe is a free and open source Android application that lets you watch and listen to content from popular streaming sites without relying on proprietary APIs or Google services. It is designed to be lightweight and respects your privacy.

## Supported Services
- YouTube and YouTube Music
- PeerTube and compatible instances
- Bandcamp
- SoundCloud
- media.ccc.de

## Features
- Play videos at up to 4K resolution
- Background playback with audio-only mode
- Popup player (picture-in-picture)
- Live stream support
- Optional subtitles and closed captions
- Search for videos, audio tracks, channels and playlists
- Subscribe to channels without an account
- Notifications for new channel uploads
- Channel groups and feed views
- Local and remote playlists
- Download videos, audio and subtitles
- Kodi integration
- Optionally hide comments, related videos or other information

## Building the App
NewPipe is built with Kotlin and Jetpack Compose. To compile a debug APK you need the Android SDK. Once installed, create a `local.properties` file in the project root and set the SDK path:

```
sdk.dir=/path/to/Android/sdk
```

Then run the Gradle task:

```bash
./gradlew assembleDebug
```

The generated APK will be located in `app/build/outputs/apk/debug/`.

## Installation
- The recommended way to install NewPipe is via the [F-Droid repository](https://newpipe.net/FAQ/tutorials/install-add-fdroid-repo/).
- You can also download APKs from the [GitHub Releases](https://github.com/TeamNewPipe/NewPipe/releases) page and verify the signing key.

## Contributing
Contributions are welcome! Please read the guidelines in [CONTRIBUTING.md](.github/CONTRIBUTING.md) before opening an issue or pull request.

## License
NewPipe is licensed under the [GNU General Public License v3](https://www.gnu.org/licenses/gpl-3.0.en.html).
