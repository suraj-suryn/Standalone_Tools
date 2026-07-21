# Plan — CSV Swiss Army Knife

## Goal
Local Spring Boot web app with pluggable CSV operations: filter, join, dedup, rename, export.

## Phase 1 — Setup
1. Spring Boot 3.x + OpenCSV + Apache POI + Thymeleaf
2. Port 8084
3. `index.html` — operation tabs: Filter / Join / Deduplicate / Rename

## Phase 2 — Core Read/Write
4. `CsvReaderService.read(MultipartFile)` → `List<Map<String, String>>`
5. `ExportService.toCsv(data, filename)` → downloadable response
6. `ExportService.toExcel(data, filename)` → downloadable .xlsx

## Phase 3 — Operations
7. `FilterService.filter(data, column, operator, value)` — operators: equals, contains, startsWith, gt, lt
8. `DeduplicateService.deduplicate(data, List<column>)` — keep first occurrence
9. `JoinService.join(dataA, dataB, keyColumn, joinType)` — INNER / LEFT
10. Column rename — accept JSON map `{oldName: newName}`, apply to all rows

## Phase 4 — Preview UI
11. `preview.html` — paginated table (100 rows), column headers, row count
12. JS: dynamic column selection dropdowns populated after file upload
13. Download buttons: CSV | Excel

## Phase 5 — Testing
14. `FilterServiceTest` — filter by equals, contains, gt on numeric column
15. `JoinServiceTest` — inner join on matching key, left join with unmatched rows
16. `DeduplicateServiceTest` — assert correct row removal

## Verification
- Upload `employees.csv`, filter by department = "Engineering" → correct subset
- Join employees + departments on `dept_id` → correct merged output
- Dedup on email column → no duplicate emails in result
- Export to Excel → file opens correctly in Excel
