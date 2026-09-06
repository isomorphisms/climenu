# climenu

A deliberately small Android menu using Material Design 3.

The useful UI source is `Climenu.kt` at the repository top level. Android/Gradle plumbing lives under `_ /android` (without the space: `_/android`).

## First slice

- Material 3 list UI
- system dark/light theme
- Android 12+ dynamic color
- prompt and choices can be supplied as Activity intent extras
- tap a row to select it
- no JNI yet

## Build

Requires JDK 17, Android SDK 37, and Gradle 9.6.0.

```sh
./build
```

This leaves the useful artifact at:

```text
climenu.apk
```

Install and launch:

```sh
adb install -r climenu.apk
adb shell am start -n org.isomorphisms.climenu/.MainActivity
```

Pass a prompt and choices from a shell:

```sh
adb shell am start -n org.isomorphisms.climenu/.MainActivity \
  --es prompt 'Choose an action' \
  --esa choices 'one,two,three'
```

## Acceptance

### Hosted build

The hosted boundary is deliberately smaller than a device receipt:

1. JDK 17, Android SDK platform 37.0, Android Build Tools 36.0.0, and Gradle 9.6.0 are available.
2. `./build` completes and produces `climenu.apk`.
3. `climenu.apk` is non-empty and passes ZIP integrity checking.

Passing this boundary proves that the Android project compiles and packages. It does **not** prove installation, launch, rendering, dynamic color, or interaction on a device.

### Device receipt still required

1. The APK installs and starts on an Android device.
2. The screen renders the Material 3 prompt and requested choices.
3. Tapping a row marks exactly that row selected and displays its value below the list.
4. Android 12+ dynamic color is checked on a device that supports it.

JNI/native/Idriç integration comes after these boundaries are trustworthy; it should carry actual menu data/actions rather than exist only as a placeholder.
