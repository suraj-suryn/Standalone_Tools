# Tool 04 — CSV Swiss Army Knife

## Overview
A local web tool to filter, transform, join, deduplicate, and export CSV files
without uploading to any cloud service.

## Problem It Solves
- Excel is overkill and slow for quick CSV transformations
- No clean offline web tool with a simple UI exists for CSV manipulation
- Business analysts and developers constantly need quick CSV operations

## Features
- Upload one or two CSV files
- **Filter:** keep rows matching a column condition
- **Rename columns:** batch rename via mapping
- **Deduplicate:** remove duplicate rows by selected columns
- **Join:** inner/left join two CSVs on a common key column
- **Transform:** apply simple expressions (trim, uppercase, date format)
- Preview result in table (first 100 rows)
- Export result as CSV or Excel

## Tech Stack
- Java 17
- Spring Boot 3.x
- OpenCSV
- Apache POI (export to Excel)
- Thymeleaf + vanilla JS (UI)
- Maven build

## Folder Structure
```
04-csv-swiss-army-knife/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/csvknife/
    │   │       ├── CsvKnifeApplication.java
    │   │       ├── controller/
    │   │       │   └── CsvController.java          # Upload + operation routing
    │   │       ├── service/
    │   │       │   ├── CsvReaderService.java        # Parse CSV → List<Map>
    │   │       │   ├── FilterService.java           # Row filter logic
    │   │       │   ├── JoinService.java             # Inner/left join logic
    │   │       │   ├── DeduplicateService.java      # Dedup by column list
    │   │       │   └── ExportService.java           # CSV + Excel export
    │   │       └── model/
    │   │           └── CsvOperation.java            # Operation type + params
    │   └── resources/
    │       ├── templates/
    │       │   ├── index.html                       # Operation selector + upload
    │       │   └── preview.html                     # Result table + download
    │       ├── static/
    │       │   ├── css/style.css
    │       │   └── js/app.js
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/csvknife/
                ├── FilterServiceTest.java
                ├── JoinServiceTest.java
                └── sample-data/
                    ├── employees.csv
                    └── departments.csv
```

## Status
- [ ] Project scaffold
- [ ] CSV upload + parse
- [ ] Filter rows operation
- [ ] Deduplicate operation
- [ ] Join two CSVs
- [ ] Column rename
- [ ] Preview table (first 100 rows)
- [ ] CSV export
- [ ] Excel export
