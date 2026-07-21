# Plan — JSON / XML Diff Tool

## Goal
Local Spring Boot web app: paste two JSON/XML documents, get a semantic tree-view diff.

## Phase 1 — Setup
1. Spring Boot 3.x + Jackson + JDOM2 + Thymeleaf, port 8086
2. `index.html`: two side-by-side text areas + "Compare" button + result panel below

## Phase 2 — Diff Logic
3. `DiffRouter.detect(input)` — if starts with `{` or `[` → JSON, if starts with `<` → XML
4. `JsonDiffService.diff(jsonA, jsonB)` → recursive walk of Jackson `JsonNode` tree:
   - Added path: in B not in A → DiffEntry(ADDED, path, null, newVal)
   - Removed path: in A not in B → DiffEntry(REMOVED, path, oldVal, null)
   - Changed: same path, different value → DiffEntry(CHANGED, path, oldVal, newVal)
5. `XmlDiffService.diff(xmlA, xmlB)` — JDOM2 parse → recursive element comparison

## Phase 3 — Results UI
6. `DiffController.compare(jsonA, jsonB)` → returns `DiffResult` as JSON to frontend
7. `diff-render.js` — builds HTML tree from diff JSON:
   - Green row: ADDED
   - Red row: REMOVED
   - Yellow row: CHANGED (shows old → new value)
8. Summary badge: X added, Y removed, Z changed

## Phase 4 — Polish
9. "Ignore whitespace" checkbox — normalize strings before comparison
10. "Flat view" toggle — collapses tree into simple path list
11. Copy summary button

## Phase 5 — Testing
12. `JsonDiffServiceTest` — nested object diff, array diff, type change
13. `XmlDiffServiceTest` — attribute change, element added/removed

## Verification
- Paste two similar JSON payloads → verify changed fields highlighted correctly
- Test deeply nested JSON (3+ levels) — verify path shown as `a.b.c`
- Paste two XML documents → verify element diffs detected
- Empty input → graceful error message (not stack trace)
