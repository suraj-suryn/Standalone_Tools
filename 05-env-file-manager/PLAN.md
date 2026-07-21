# Plan — ENV File Manager

## Goal
JavaFX desktop app to manage multi-project, multi-profile `.env` files securely offline.

## Phase 1 — Setup
1. JavaFX 21 + Jackson + Maven
2. Main window: left sidebar (project list), right panel (key-value table)
3. `main.fxml` layout

## Phase 2 — File I/O
4. `EnvFileService.read(Path)` → `EnvProfile` (parse `KEY=VALUE` line by line, skip comments `#`)
5. `EnvFileService.write(EnvProfile, Path)` → write back to `.env` format
6. Store project registry in `~/.env-manager/projects.json` (Jackson)

## Phase 3 — UI: View + Edit
7. TableView with columns: Key | Value (masked ***) | Unmask toggle button
8. Add row (dialog), Edit row (inline), Delete row
9. Mark entries as secret (values always masked, never shown in export preview)

## Phase 4 — Profiles
10. `ProfileService` — each project has named profiles stored in subfolders
11. Dropdown to switch active profile → reloads table
12. Copy value from one profile to another

## Phase 5 — Export + Search
13. Export active profile to `.env` file (file picker)
14. Search bar — filter table rows by key name

## Phase 6 — Testing
15. `EnvFileServiceTest` — read a test `.env` file, assert correct key count and values
16. Round-trip test: read → write → re-read → assert identical

## Verification
- Open a real project `.env` → values display masked
- Edit a value → re-open file → verify saved correctly
- Switch profile → table updates with different values
- Export → resulting `.env` is valid format
