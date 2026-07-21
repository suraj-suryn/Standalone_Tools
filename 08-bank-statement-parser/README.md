# Tool 08 — Bank Statement Parser

## Overview
Upload a PDF bank statement and extract transactions as structured CSV/JSON/Excel.
Works fully offline — no cloud OCR, no data leaves your machine.

## Problem It Solves
- Bank statements are PDF — not directly usable for analysis or reconciliation
- Cloud PDF tools (Adobe, Smallpdf) send sensitive financial data to their servers
- No free offline tool converts bank statement PDFs to structured data

## Features
- Upload PDF bank statement
- Extract transaction rows: Date, Description, Debit, Credit, Balance
- Preview extracted data in browser table
- Handle common bank PDF layouts (text-based, not scanned images)
- Export as CSV, JSON, or Excel
- Support multiple bank statement formats via configurable templates

## Tech Stack
- Java 17
- Spring Boot 3.x
- Apache PDFBox (PDF text extraction)
- Apache POI (Excel export)
- OpenCSV (CSV export)
- Thymeleaf (UI)
- Maven build

## Folder Structure
```
08-bank-statement-parser/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/statementparser/
    │   │       ├── StatementParserApplication.java
    │   │       ├── controller/
    │   │       │   └── ParserController.java         # Upload + parse + export
    │   │       ├── service/
    │   │       │   ├── PdfExtractorService.java      # PDFBox text extraction
    │   │       │   ├── TransactionParserService.java # Regex to find transaction rows
    │   │       │   └── ExportService.java            # CSV / Excel / JSON export
    │   │       └── model/
    │   │           └── Transaction.java              # Date, description, debit, credit, balance
    │   └── resources/
    │       ├── templates/
    │       │   ├── index.html                        # Upload form
    │       │   └── result.html                       # Transaction table + download
    │       ├── static/css/style.css
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/statementparser/
                ├── TransactionParserServiceTest.java
                └── sample-pdfs/
                    └── sample-statement.pdf          # Synthetic test PDF
```

## Status
- [ ] Project scaffold
- [ ] PDF text extraction (PDFBox)
- [ ] Transaction row detection (regex)
- [ ] Preview table
- [ ] CSV export
- [ ] Excel export
- [ ] JSON export
- [ ] Multi-bank layout support
