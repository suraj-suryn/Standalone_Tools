# Tool 01 — JWT Debugger (Offline)

## Overview
A fully offline JWT (JSON Web Token) inspector and debugger.  
Built for security-conscious developers in fintech/banking who cannot use online tools like jwt.io.

## Problem It Solves
- Online JWT decoders (jwt.io) send tokens over the internet — unacceptable for banking tokens
- No clean offline desktop tool exists with signature validation support

## Features
- Decode JWT header and payload (Base64URL decoding)
- Validate signature offline (HS256, RS256, RS512)
- Check token expiry (`exp`) and issued-at (`iat`) timestamps
- Highlight claims in color (expired = red, valid = green)
- Support pasting raw token or loading from file
- Copy decoded JSON to clipboard

## Tech Stack
- Java 17
- JavaFX (desktop UI — no browser, no server)
- JJWT library (io.jsonwebtoken) for parsing and validation
- Maven build

## Folder Structure
```
01-jwt-debugger/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/jwtdebugger/
    │   │       ├── JwtDebuggerApp.java          # JavaFX Application entry point
    │   │       ├── controller/
    │   │       │   └── MainController.java       # UI event handling
    │   │       ├── service/
    │   │       │   └── JwtParserService.java     # Decode + validate JWT
    │   │       └── model/
    │   │           └── JwtPayload.java           # Parsed token model
    │   └── resources/
    │       ├── fxml/
    │       │   └── main.fxml                     # JavaFX UI layout
    │       └── css/
    │           └── style.css
    └── test/
        └── java/
            └── com/tools/jwtdebugger/
                └── JwtParserServiceTest.java
```

## Status
- [ ] Project scaffold
- [ ] JWT decode (header + payload)
- [ ] Signature validation (HS256)
- [ ] Signature validation (RS256/RS512)
- [ ] Expiry check UI
- [ ] File load support
- [ ] Clipboard copy
