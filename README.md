# Memento Widgets (Android)

Two independent home-screen widgets in one native Android app:

- **Life Progress**: percentage of an assumed lifespan elapsed, time lived, and estimated remaining time. Uses your chosen date of birth and lifespan. Updates on date change, timezone change, reboot, app update, and Android's periodic widget refresh (Android may defer periodic updates).
- **My Quotes**: personal quotes stored on-device. Add, edit, and delete them in the app; use ‹ and › buttons on the home-screen widget to navigate. The buttons are used instead of swipes because standard Android home-screen widgets don't support arbitrary horizontal swipe gestures reliably.

## Install

1. Open the `MementoWidgets` folder in **Android Studio**.
2. Allow Gradle to sync and install any requested Android SDK 35 components.
3. Run the `app` configuration on your phone (Android 8.0+).
4. Open the app to choose your date of birth, lifespan, and quotes.
5. Long-press an empty home-screen area → **Widgets** → **Memento Widgets** → add each widget separately.

## Technical notes

- Java + native `AppWidgetProvider`/`RemoteViews`, no external dependencies beyond Android Gradle Plugin.
- Local SharedPreferences storage; no login or internet access.
- The quote index is shared by all quote-widget instances.
- The life widget's progress bar is updated using integer precision (0–10000).
- Default date of birth is a **placeholder**: set your actual date in the app.
- Tested by static inspection only; no Android SDK/Gradle build environment was available in this session.

## Build APK without Android Studio (GitHub Actions)

1. Create a GitHub repository and upload **the contents** of this `MementoWidgets` folder to the repository root, including the hidden `.github` folder. Alternatively, push this folder with Git.
2. In GitHub, open **Actions** → **Build installable APK** → **Run workflow** (or push to `main`/`master`).
3. After the workflow succeeds, open the run and download the `MementoWidgets-debug-apk` artifact.
4. Unzip the artifact to get `app-debug.apk`, transfer it to your phone, and open it to install. Android may ask you to allow installs from the app you used to open the APK.

This is a debug-signed APK suitable for personal testing, not a Play Store release. If the build fails, share the build log for troubleshooting. The workflow has not been run or verified in this environment.
