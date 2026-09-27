# PALASH Android App

This folder contains a real Android application project for the PALASH mother-tongue learning assistant.

## Project structure

- app/ — Android app module
- build.gradle.kts — root Gradle configuration
- settings.gradle.kts — project settings
- gradle.properties — Gradle properties

## What the app does

- shows a teacher-facing translation UI
- allows language switching between Santhali, Ho, and Mundari
- handles FLN-style lesson generation
- displays bilingual worksheet content
- is structured for future offline speech and content sync features

## Build steps

1. Install Android Studio.
2. Open this folder as the project root.
3. Sync Gradle.
4. Run the app on a connected Android device or emulator.

## Notes

This is a production-ready Android app structure, but it still uses a local demo translation layer at the MVP stage. The next engineering step would be to integrate a production translation model, TTS voices, speech recognition, and offline data sync.
