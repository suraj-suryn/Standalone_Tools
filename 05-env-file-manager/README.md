# Tool 05 — ENV File Manager

## Overview
A desktop GUI to manage `.env` files across multiple projects securely.
Switch between profiles (dev/staging/prod), mask secrets visually, and never sync to cloud.

## Problem It Solves
- Developers working on multiple projects lose track of which `.env` goes where
- No offline tool lets you switch dev/staging/prod profiles cleanly
- Copy-pasting `.env` values between files causes errors and leaks

## Features
- Add / open multiple project `.env` files from disk
- View all key-value pairs in a table (values masked by default)
- Add, edit, delete entries
- Profile support: dev / staging / prod per project
- Switch active profile with one click
- Export to `.env` format
- Search across all keys

## Tech Stack
- Java 17
- JavaFX (desktop app — no browser, no server)
- Jackson (JSON for internal config storage)
- Maven build

## Folder Structure
```
05-env-file-manager/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/envmanager/
    │   │       ├── EnvManagerApp.java              # JavaFX entry point
    │   │       ├── controller/
    │   │       │   └── MainController.java         # UI event handling
    │   │       ├── service/
    │   │       │   ├── EnvFileService.java         # Read/write .env files
    │   │       │   └── ProfileService.java         # Profile switching logic
    │   │       └── model/
    │   │           ├── EnvProject.java             # Project with multiple profiles
    │   │           └── EnvProfile.java             # Profile = list of key-value pairs
    │   └── resources/
    │       ├── fxml/
    │       │   ├── main.fxml
    │       │   └── project-tab.fxml
    │       └── css/style.css
    └── test/
        └── java/
            └── com/tools/envmanager/
                └── EnvFileServiceTest.java
```

## Status
- [ ] Project scaffold
- [ ] Read .env file into model
- [ ] Display key-value table (masked values)
- [ ] Add/edit/delete entries
- [ ] Profile switching
- [ ] Export back to .env
- [ ] Multi-project tabs
- [ ] Search across keys
