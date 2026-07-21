# Plan — Transaction Reconciliation Tool

## Goal
Build a Spring Boot web app that compares two uploaded transaction files (CSV/Excel),
identifies matches, discrepancies, and missing records, then exports a colored report.

## Phase 1 — Project Setup
1. Spring Boot 3.x with POI, OpenCSV, Thymeleaf
2. `application.properties`: port 8083, max file size 20MB
3. `index.html` — two file upload fields + match key input (column name)

## Phase 2 — File Reading
4. `FileReaderService.readCsv(MultipartFile)` → `List<Map<String, String>>` (column→value)
5. `FileReaderService.readExcel(MultipartFile)` → same generic format
6. Auto-detect file type by extension

## Phase 3 — Reconciliation Logic
7. `ReconciliationService.reconcile(listA, listB, matchKey)`:
   - Build Map<String, Map> from each list using matchKey as lookup
   - Matched: same key in both, all values equal
   - Amount mismatch: same key, differing amount/value column
   - Missing in B: key in A not in B
   - Missing in A: key in B not in A
8. Return `ReconciliationResult` with 4 lists

## Phase 4 — Results UI
9. `result.html` — 4 sections with color coding:
   - Green: matched
   - Yellow: amount mismatch
   - Red: missing in A or B
10. Summary count badges at top

## Phase 5 — Export
11. `ReportExportService.toExcel(result)` — 4 sheets (one per category) with Apache POI
12. `/report/download` endpoint streams the Excel file

## Phase 6 — Testing
13. Unit test `ReconciliationService` with `sample-data/file-a.csv` + `file-b.csv`
14. Assert: correct matched count, correct mismatch detection

## Verification
- Upload two CSV files with known differences → verify all 4 categories correct
- Download Excel report → verify 4 sheets open correctly
- Test with Excel (.xlsx) input files
