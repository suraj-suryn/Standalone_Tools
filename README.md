# Standalone Tools

A collection of standalone developer and productivity tools built for daily use.
Each tool is self-contained, offline-first, and built with Java/Spring Boot.

---

## Tools Index

| # | Tool | Tech | Status | Priority |
|---|------|------|--------|----------|
| 01 | [JWT Debugger (Offline)](./01-jwt-debugger/README.md) | Java + JavaFX | Planning | HIGH |
| 02 | [Log File Analyzer](./02-log-file-analyzer/README.md) | Spring Boot + Web UI | Planning | HIGH |
| 03 | [Transaction Reconciliation](./03-transaction-reconciliation/README.md) | Spring Boot + Apache POI | Planning | HIGH |
| 04 | [CSV Swiss Army Knife](./04-csv-swiss-army-knife/README.md) | Spring Boot + Web UI | Planning | HIGH |
| 05 | [ENV File Manager](./05-env-file-manager/README.md) | JavaFX (Desktop) | Planning | MEDIUM-HIGH |
| 06 | [JSON / XML Diff Tool](./06-json-xml-diff/README.md) | Spring Boot + Web UI | Planning | MEDIUM |
| 07 | [SQL Query Explainer](./07-sql-query-explainer/README.md) | Spring Boot | Planning | MEDIUM |
| 08 | [Bank Statement Parser](./08-bank-statement-parser/README.md) | Spring Boot + PDFBox | Planning | MEDIUM |
| 09 | [Local API Mock Server](./09-api-mock-server/README.md) | Spring Boot + Web UI | Planning | MEDIUM |
| 10 | [Standup Generator](./10-standup-generator/README.md) | HTML/JS (No Backend) | Planning | LOW-MEDIUM |

---

## Build Order

```
Phase 1 — Quick Wins:    10 → 06 → 04
Phase 2 — Core Tools:    01 → 02 → 03
Phase 3 — Complex Tools: 05 → 08 → 09 → 07
```

---

## Tech Stack

- **Language:** Java 17+
- **Framework:** Spring Boot 3.x (where applicable)
- **Build:** Maven
- **UI:** Thymeleaf / plain HTML+JS (browser-based, no cloud)
- **Desktop:** JavaFX (where standalone desktop app is needed)

---

## Contributing

Each tool lives in its own folder with its own `README.md`, `PLAN.md`, and `src/` directory.
Build tools independently — they do not share dependencies.
