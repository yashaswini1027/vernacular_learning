# PALASH Vernacular Classroom Assistant

This repository contains a lightweight offline prototype for a Hindi-to-tribal-language teaching assistant designed for mother tongue-based primary education in tribal regions of Jharkhand.

## Problem addressed

Teachers who are trained in Hindi often struggle to deliver instruction in Ho, Mundari, and Santhali. The prototype demonstrates a practical, low-spec digital bridge that can help a teacher:

- translate Hindi FLN text into a target tribal language,
- generate bilingual classroom content for foundational learning,
- support real-time voice translation using local browser speech APIs,
- work offline on low-cost Android tablets after initial sync.

## Included features

- Offline-first static app with service worker caching
- Hindi → Santhali / Ho / Mundari phrase translation prototype
- Real-time teacher voice-to-language translation interface
- Auto-generated FLN lesson script for numeracy and recognition activities
- Printable bilingual worksheet output for NIPUN Bharat-aligned numeracy practice
- Tablet-friendly responsive layout for Android 9+ screens

## Directory structure

- index.html – application shell and UI
- styles.css – responsive classroom UI
- app.js – translation logic, lesson generation, worksheet generation, and voice flow
- sw.js – offline cache service worker
- manifest.json – Android installable web app metadata

## Run locally

From the project folder, start a local static server:

python -m http.server 8000

Then open:

http://localhost:8000

## Demo flow

1. Select a target language.
2. Enter a Hindi FLN text such as “गिनती के साथ बच्चों को 5 तक zählen करें।”
3. Click Translate to see a local prototype output.
4. Click Start Hindi voice translation to test the speech bridge.
5. Generate a lesson script and worksheet to simulate classroom delivery.

## Notes

This is a prototype for the requested MVP and focuses on a single-language demo path with locally mapped phrase translations and browser-based speech support. It is designed to be expanded with a production-grade tribal language NLP model and an Android wrapper in a later stage.

## Suggested next steps

- integrate a proper Hindi-to-Santhali/Ho/Mundari neural translation model,
- add TTS voices for the tribal languages,
- package as a Capacitor or Android WebView app,
- sync lesson packs through an offline content manager.
