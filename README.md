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

The first acceptance boundary is visual and intentionally narrow:

1. `./build` produces `climenu.apk`.
2. The APK installs and starts.
3. The screen uses Material 3 components.
4. It displays the requested prompt and choices.
5. Tapping a row marks exactly that row selected and prints its value below the list.

JNI/native/Idriç integration comes after this boundary is trustworthy; it should carry actual menu data/actions rather than exist only as a placeholder.
