# Tool 10 — Standup Generator

## Overview
A minimal browser tool. Fill in Yesterday / Today / Blockers and get a formatted
standup message ready to paste into Slack, Teams, or email. No backend needed.

## Problem It Solves
- Writing standup messages every day is repetitive
- Formatting differs per team (Slack, Teams, email) — constant copy-paste reformatting
- Simple task with no good dedicated tool

## Features
- Input: Yesterday (text), Today (text), Blockers (text)
- Date auto-populated
- Output formats: Slack (plain), Teams (@ mentions), Email (formatted), Plain text
- Copy to clipboard button
- Save as template for repetitive items

## Tech Stack
- Pure HTML + CSS + JavaScript (no backend, no build tool)
- Runs by opening `index.html` directly in any browser
- LocalStorage for saving templates

## Folder Structure
```
10-standup-generator/
├── README.md
├── PLAN.md
└── src/
    └── index.html          # Single file — complete app (HTML + CSS + JS inline)
```

## Usage
1. Open `src/index.html` in any browser
2. Fill in the three fields
3. Select output format
4. Click "Copy to Clipboard"

## Status
- [ ] HTML form layout
- [ ] Format generators (Slack, Teams, Email, Plain)
- [ ] Copy to clipboard
- [ ] Date auto-fill
- [ ] LocalStorage template save/load
