# Tool 03 — Transaction Reconciliation Tool

## Overview
Upload two CSV or Excel transaction files and instantly see which records match,
which are missing, and which have amount discrepancies. Export a diff report.

## Problem It Solves
- Manual reconciliation of bank/payment transaction exports is tedious and error-prone
- No free offline tool compares two transaction files with configurable match keys
- Finance teams spend hours in Excel doing VLOOKUP-based reconciliation

## Features
- Upload two CSV or Excel (.xlsx) files (File A vs File B)
- Configure match key (e.g., Transaction ID, Reference Number)
- Detect: matched, missing in A, missing in B, amount mismatch
- Show color-coded diff table in browser
- Export reconciliation report as Excel (.xlsx) or CSV

## Tech Stack
- Java 17
- Spring Boot 3.x
- Apache POI (Excel read/write)
- OpenCSV (CSV parsing)
- Thymeleaf (UI)
- Maven build

## Folder Structure
```
03-transaction-reconciliation/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/reconciliation/
    │   │       ├── ReconciliationApplication.java
    │   │       ├── controller/
    │   │       │   └── ReconciliationController.java   # Upload + config + results
    │   │       ├── service/
    │   │       │   ├── FileReaderService.java           # CSV + Excel reader → List<Map>
    │   │       │   ├── ReconciliationService.java       # Core match/diff logic
    │   │       │   └── ReportExportService.java         # Excel/CSV export
    │   │       └── model/
    │   │           ├── Transaction.java                 # Generic row model
    │   │           └── ReconciliationResult.java        # Matched/missing/mismatch lists
    │   └── resources/
    │       ├── templates/
    │       │   ├── index.html                           # Upload + key config form
    │       │   └── result.html                          # Color-coded diff table
    │       ├── static/css/style.css
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/reconciliation/
                ├── ReconciliationServiceTest.java
                └── sample-data/
                    ├── file-a.csv
                    └── file-b.csv
```

## Status
- [ ] Project scaffold
- [ ] CSV file reader
- [ ] Excel file reader
- [ ] Match key configuration UI
- [ ] Core reconciliation logic
- [ ] Results diff table (color-coded)
- [ ] Excel export report
