# Plan — Bank Statement Parser

## Goal
Spring Boot app: upload bank statement PDF → extract transactions → export CSV/Excel/JSON.

## Phase 1 — Setup
1. Spring Boot 3.x + PDFBox + POI + OpenCSV + Thymeleaf, port 8088
2. Max upload: 20MB PDF

## Phase 2 — PDF Text Extraction
3. `PdfExtractorService.extractText(MultipartFile)`:
   - PDFBox `PDDocument.load()` + `PDFTextStripper`
   - Returns raw text per page as `List<String>`
4. Handle encrypted PDF: return friendly error message

## Phase 3 — Transaction Parsing
5. `TransactionParserService.parse(List<String> pages)`:
   - Regex strategy: look for lines matching `DD/MM/YYYY` or `MM/DD/YYYY` date pattern
   - Extract: date, description (middle), debit amount, credit amount, balance
   - Pattern: `(\d{2}/\d{2}/\d{4})\s+(.+?)\s+([\d,]+\.\d{2})?\s+([\d,]+\.\d{2})?\s+([\d,]+\.\d{2})`
   - Configurable column order per bank layout
6. Return `List<Transaction>`

## Phase 4 — Preview + Export
7. `result.html` — paginated table: Date | Description | Debit | Credit | Balance
8. `ExportService.toCsv(transactions)` → downloadable CSV
9. `ExportService.toExcel(transactions)` → formatted .xlsx with header row
10. `ExportService.toJson(transactions)` → pretty-printed JSON

## Phase 5 — Multi-Layout Support
11. `LayoutConfig` — enum of supported banks with regex patterns
12. Auto-detect layout or let user select from dropdown

## Phase 6 — Testing
13. `TransactionParserServiceTest` — parse known text block, assert correct Transaction list
14. Use synthetic `sample-statement.pdf` (generated in test setup)

## Verification
- Upload a real (non-sensitive) bank statement → verify transaction count matches PDF
- Amounts parse correctly (debit vs credit columns)
- CSV download — opens in Excel with correct columns
- JSON download — valid JSON structure
