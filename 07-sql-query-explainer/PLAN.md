# Plan — SQL Query Explainer

## Goal
Rule-based EXPLAIN analyzer: parse PostgreSQL/MySQL EXPLAIN output, apply rules,
produce plain-English suggestions without needing a live DB or LLM.

## Phase 1 — Setup
1. Spring Boot 3.x + Thymeleaf, port 8087
2. `index.html`: SQL textarea, EXPLAIN textarea, dialect selector (PostgreSQL/MySQL), Analyze button

## Phase 2 — Parser
3. `ExplainParserService.parsePostgres(text)`:
   - Regex line-by-line: extract node type (Seq Scan, Index Scan, Hash Join, Nested Loop...)
   - Extract table name, cost estimate, rows, width
   - Return `List<ExplainNode>`
4. `ExplainParserService.parseMysql(text)`:
   - Parse tabular EXPLAIN output (id, select_type, table, type, key, rows...)
   - Return same `List<ExplainNode>`

## Phase 3 — Rule Engine
5. `RuleEngine.analyze(List<ExplainNode>)` — apply rules:
   - Rule: `Seq Scan` on large table → "Full table scan — consider adding index on [column]"
   - Rule: `Nested Loop` with high row estimate → "Nested loop join may be slow — verify join columns are indexed"
   - Rule: `Hash Join` → informational — "Hash join: good for large datasets"
   - Rule: `cost > 1000` → "High cost query — review WHERE clauses and indexes"
   - Rule: MySQL `type=ALL` → "Full table scan detected"
6. Return `List<Suggestion>` with severity (INFO/WARN/CRITICAL)

## Phase 4 — UI
7. Display suggestions as cards (red=CRITICAL, yellow=WARN, blue=INFO)
8. Show parsed plan as expandable tree (node type + cost + rows)
9. Show generated index SQL: `CREATE INDEX idx_table_col ON table(col);`

## Phase 5 — Testing
10. `ExplainParserServiceTest` — parse known PostgreSQL EXPLAIN output, assert node types
11. `RuleEngineTest` — feed Seq Scan node → assert CRITICAL suggestion returned

## Verification
- Paste a real PostgreSQL EXPLAIN with Seq Scan → see index suggestion
- Paste MySQL EXPLAIN with type=ALL → see critical warning
- Clean query with Index Scan only → no warnings shown
