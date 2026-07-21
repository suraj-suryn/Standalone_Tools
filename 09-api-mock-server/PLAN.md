# Plan — Local API Mock Server

## Goal
Spring Boot app with management UI to define mock endpoints and serve them on a local port.

## Phase 1 — Setup
1. Spring Boot 3.x + Thymeleaf + Jackson, management UI port 8089
2. Mock dispatch runs on a secondary embedded Tomcat (TomcatServletWebServerFactory) on port 9000
3. `index.html`: table of mock rules + "Add Endpoint" button

## Phase 2 — Mock Registry
4. `MockRegistryService` — `List<MockEndpoint>` in memory (thread-safe CopyOnWriteArrayList)
5. `MockEndpoint` fields: id, method, path, statusCode, responseBody, contentType, delayMs
6. CRUD endpoints: POST /mock, DELETE /mock/{id}, GET /mock (list)

## Phase 3 — Request Dispatch
7. `MockDispatchController` — `@RequestMapping("/**")` catches all requests on mock port
8. `MockMatcherService.match(method, path)` — scan registry, first match wins
9. If matched: apply delay (`Thread.sleep(delayMs)`), return configured response
10. If unmatched: return 404 with body `{"error": "No mock rule matched"}`

## Phase 4 — Request Logging
11. `RequestLogService` — store last 100 requests (circular buffer) with timestamp, method, path, matched rule id
12. `/logs` page — auto-refresh every 3 seconds

## Phase 5 — Persistence
13. POST `/config/save` → serialize mock rules to `mock-config.json` in working directory
14. POST `/config/load` → load from file and replace in-memory registry

## Phase 6 — Testing
15. `MockMatcherServiceTest` — exact path match, wildcard path, method mismatch → no match
16. `MockRegistryServiceTest` — add, list, delete rules

## Verification
- Add a GET /api/users mock → call localhost:9000/api/users → verify correct response
- Add delay 2000ms → verify response takes ~2s
- View logs → request appears
- Save config → delete all rules → load config → rules restored
