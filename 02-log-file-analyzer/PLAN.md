# Plan — Log File Analyzer

## Goal
Build a local Spring Boot web app where users upload a log file and get a visual summary
with error timeline, pattern detection, and exportable report.

## Phase 1 — Project Setup
1. Spring Boot 3.x project with Thymeleaf + Commons IO
2. `application.properties`: `server.port=8082`, `spring.servlet.multipart.max-file-size=50MB`
3. `index.html` — file upload form

## Phase 2 — Upload + Parse
4. `LogController.uploadFile()` — accepts MultipartFile, calls `LogParserService`
5. `LogParserService.parse(InputStream)` — line-by-line scan:
   - Regex to extract: `timestamp`, `level` (ERROR/WARN/INFO/DEBUG), `message`
   - Support formats: Log4j, Logback, plain text
6. Return `LogSummary` (counts by level, list of `LogEntry` with timestamp + level + message)

## Phase 3 — Results UI
7. `result.html` — show summary table (total lines, ERROR count, WARN count)
8. Timeline chart (Chart.js) — X = hour, Y = ERROR count per hour
9. Scrollable filtered log table — clickable level badges

## Phase 4 — Filter + Export
10. Filter by keyword (client-side JS search on the log table)
11. `ReportService.generateHtml(LogSummary)` — standalone HTML report
12. Download report endpoint `/report/download`

## Phase 5 — Testing
13. Unit test `LogParserService` with `sample-logs/test.log` (known content, assert counts)
14. Integration test: upload file → assert summary response

## Verification
- Upload a Spring Boot log file → verify ERROR/WARN counts match manual grep
- Timeline shows correct distribution
- Downloaded report opens correctly in browser with no internet required
