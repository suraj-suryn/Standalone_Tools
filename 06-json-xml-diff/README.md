# Tool 06 — JSON / XML Diff Tool

## Overview
Paste two JSON or XML blocks side-by-side and instantly see what changed:
added fields, removed fields, value changes — with a clean tree-view diff.

## Problem It Solves
- Most JSON diff tools are online (jsoncompare.com etc.) — not suitable for sensitive payloads
- Text-based diff tools show raw character diffs, not semantic structure diffs
- No free offline tool with proper tree-view diff for both JSON and XML

## Features
- Paste JSON or XML in two text areas (auto-detects format)
- Tree-view diff: color-coded added (green), removed (red), changed (yellow)
- Flat diff view: shows only differing paths
- Ignore whitespace option
- Copy diff summary to clipboard

## Tech Stack
- Java 17
- Spring Boot 3.x
- Jackson (JSON parsing/comparison)
- JDOM2 (XML parsing)
- Thymeleaf (UI)
- Maven build

## Folder Structure
```
06-json-xml-diff/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/difftool/
    │   │       ├── DiffToolApplication.java
    │   │       ├── controller/
    │   │       │   └── DiffController.java         # POST /diff endpoint
    │   │       ├── service/
    │   │       │   ├── JsonDiffService.java         # Deep JSON comparison
    │   │       │   ├── XmlDiffService.java          # XML comparison
    │   │       │   └── DiffRouter.java              # Auto-detect + route
    │   │       └── model/
    │   │           └── DiffResult.java              # List of DiffEntry (path, type, oldVal, newVal)
    │   └── resources/
    │       ├── templates/
    │       │   └── index.html                       # Two panes + diff result
    │       ├── static/
    │       │   ├── css/style.css
    │       │   └── js/diff-render.js                # Tree rendering
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/difftool/
                ├── JsonDiffServiceTest.java
                └── XmlDiffServiceTest.java
```

## Status
- [ ] Project scaffold
- [ ] JSON deep diff
- [ ] XML diff
- [ ] Auto-detect format
- [ ] Tree-view UI render
- [ ] Flat diff view
- [ ] Copy to clipboard
