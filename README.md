# climenu

A deliberately small Android menu using Material Design 3.

The useful UI source is `Climenu.kt` at the repository top level. Android/Gradle plumbing lives under `_ /android` (without the space: `_/android`).

## First substantial example: `gh`

With no Activity extras, `climenu` now opens a GitHub CLI (`gh`) command menu. The compact command fixture is in `GhExample.kt` and `examples/gh/command_tree.tsv`.

`examples/gh/` also records the authoritative manual/documentation sources and how the actual `gh` parser is assembled. Run:

```sh
sh ./import-gh
```

to materialize the complete `gh help`/`gh help reference` corpus, the pinned generated manpage tree when a suitable Go toolchain is available, and the upstream Cobra command/parser source into `examples/gh/imported/`.

The current fixture is pinned to GitHub CLI 2.100.0.

## First slice

- Material 3 list UI
- system dark/light theme
- Android 12+ dynamic color
- `gh` root commands as the default example
- prompt and choices can still be supplied as Activity intent extras
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
2. With no extras, the screen renders the Material 3 `gh` command fixture.
3. Tapping a row marks exactly that row selected and displays its value below the list.
4. Explicit `prompt`/`choices` extras override the `gh` fixture.
5. Android 12+ dynamic color is checked on a device that supports it.

JNI/native/Idriç integration comes after these boundaries are trustworthy; it should carry actual menu data/actions rather than exist only as a placeholder.
