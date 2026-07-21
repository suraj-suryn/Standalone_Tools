# Plan — JWT Debugger (Offline)

## Goal
Build an offline JavaFX desktop app to decode, inspect, and validate JWT tokens securely.

## Phase 1 — Project Setup
1. Initialize Maven project with JavaFX + JJWT dependencies
2. Create `JwtDebuggerApp.java` extending `javafx.application.Application`
3. Design `main.fxml` layout — text area (input), two panels (header/payload), status bar

## Phase 2 — Core Decode
4. Implement `JwtParserService.decode(token)` — split by `.`, Base64URL decode header + payload
5. Pretty-print decoded JSON in UI panels
6. Handle malformed token gracefully (invalid format error message)

## Phase 3 — Validation
7. Implement HS256 signature validation — user provides secret key
8. Implement RS256/RS512 validation — user provides public key (PEM)
9. Show VALID / INVALID / UNVERIFIED badge in UI

## Phase 4 — UX Polish
10. Color-code expiry: green (valid), orange (< 5 min), red (expired)
11. Load token from `.txt` or `.json` file via file picker
12. Copy decoded JSON to clipboard button

## Phase 5 — Testing
13. Unit tests for `JwtParserService` — valid token, expired, malformed, wrong signature
14. Manual test checklist

## Dependencies
```xml
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt-api</artifactId>
    <version>0.12.5</version>
</dependency>
<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-controls</artifactId>
    <version>21</version>
</dependency>
```

## Verification
- Decode a known JWT and compare payload to jwt.io (offline reference)
- Validate HS256 with a known secret — expect VALID
- Provide wrong secret — expect INVALID
- Load expired token — expect red expiry indicator
