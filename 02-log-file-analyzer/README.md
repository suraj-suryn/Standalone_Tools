# Tool 02 — Log File Analyzer

## Overview
Upload a log file and get instant pattern analysis: ERROR/WARN frequency, timeline view,
anomaly detection, and exportable HTML report. Fully offline — runs as a local Spring Boot app.

## Problem It Solves
- Developers manually grep large log files — slow and error-prone
- No free offline tool gives a visual timeline + summary report
- Cloud-based tools (Papertrail, Datadog) cannot be used with sensitive banking logs

## Features
- Upload `.log` / `.txt` file via browser
- Parse and count ERROR, WARN, INFO, DEBUG lines
- Show timeline of errors by hour/minute
- Pattern detection: repeated stack traces, high-frequency errors
- Filter by log level or keyword
- Export summary as HTML report

## Tech Stack
- Java 17
- Spring Boot 3.x
- Thymeleaf (server-side rendered UI)
- Apache Commons IO (file handling)
- Chart.js (timeline chart via CDN-free local bundle)
- Maven build

## Folder Structure
```
02-log-file-analyzer/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/loganalyzer/
    │   │       ├── LogAnalyzerApplication.java
    │   │       ├── controller/
    │   │       │   └── LogController.java         # Upload + render results
    │   │       ├── service/
    │   │       │   ├── LogParserService.java       # Core parsing logic
    │   │       │   └── ReportService.java          # Build HTML report
    │   │       └── model/
    │   │           ├── LogEntry.java               # Single parsed log line
    │   │           └── LogSummary.java             # Aggregated stats
    │   └── resources/
    │       ├── templates/
    │       │   ├── index.html                      # Upload page
    │       │   └── result.html                     # Results + chart
    │       ├── static/
    │       │   ├── css/style.css
    │       │   └── js/chart.min.js                 # Bundled Chart.js (offline)
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/loganalyzer/
                ├── LogParserServiceTest.java
                └── sample-logs/
                    └── test.log
```

## Status
- [ ] Project scaffold
- [ ] File upload endpoint
- [ ] Log line parser (level + timestamp extraction)
- [ ] Summary aggregation
- [ ] Timeline chart
- [ ] Keyword filter
- [ ] Export HTML report
