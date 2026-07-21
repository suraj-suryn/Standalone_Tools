# Tool 07 — SQL Query Explainer

## Overview
Paste a SQL query and its EXPLAIN output, get a plain-English explanation
with optimization suggestions — no database connection required.

## Problem It Solves
- EXPLAIN output is cryptic for developers who aren't DBAs
- Most SQL optimization tools require a live DB connection or are cloud-based
- Developers waste time decoding "seq scan", "nested loop", "bitmap heap scan" etc.

## Features
- Paste SQL query in one panel, EXPLAIN/EXPLAIN ANALYZE output in another
- Plain-English breakdown of each operation (e.g., "Full table scan on users — consider adding index")
- Detect common anti-patterns: N+1 hint, missing index suggestion, seq scan warning
- Supports PostgreSQL and MySQL EXPLAIN formats
- Suggest index creation statements where applicable

## Tech Stack
- Java 17
- Spring Boot 3.x
- Thymeleaf (UI)
- Rule-based analysis engine (no external LLM required)
- Maven build

## Folder Structure
```
07-sql-query-explainer/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/sqlexplainer/
    │   │       ├── SqlExplainerApplication.java
    │   │       ├── controller/
    │   │       │   └── ExplainController.java      # POST /explain
    │   │       ├── service/
    │   │       │   ├── ExplainParserService.java    # Parse EXPLAIN text → structured nodes
    │   │       │   ├── RuleEngine.java              # Apply analysis rules
    │   │       │   └── SuggestionService.java       # Generate plain-English suggestions
    │   │       └── model/
    │   │           ├── ExplainNode.java             # Single explain plan step
    │   │           └── ExplainResult.java           # Full analysis + suggestions
    │   └── resources/
    │       ├── templates/
    │       │   └── index.html                       # SQL + EXPLAIN input + results
    │       ├── static/css/style.css
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/sqlexplainer/
                ├── ExplainParserServiceTest.java
                └── RuleEngineTest.java
```

## Status
- [ ] Project scaffold
- [ ] EXPLAIN text parser (PostgreSQL format)
- [ ] EXPLAIN parser (MySQL format)
- [ ] Rule engine: seq scan, missing index, nested loop
- [ ] Plain-English output
- [ ] Index suggestion generator
- [ ] UI
