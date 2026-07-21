# Tool 09 — Local API Mock Server

## Overview
A UI-driven local mock server. Define endpoints, response bodies, status codes, and delays
through a browser UI — no YAML/JSON config files required. Save and reload configurations.

## Problem It Solves
- WireMock is powerful but requires YAML/JSON config — steep learning curve
- Mockoon is good but is an Electron app requiring installation
- Developers need a quick way to mock APIs during frontend development or testing

## Features
- Create mock endpoints via UI (method, path, status code, response body, delay)
- Start/stop the mock server on a configurable port
- View incoming request log in real time
- Save configurations to file and reload
- Support response templating (e.g., echo request body field)

## Tech Stack
- Java 17
- Spring Boot 3.x (two embedded servers: UI on 8089, mock server on user-defined port)
- Thymeleaf (UI for management)
- Jackson (response body JSON handling)
- Maven build

## Folder Structure
```
09-api-mock-server/
├── README.md
├── PLAN.md
├── pom.xml
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/tools/mockserver/
    │   │       ├── MockServerApplication.java
    │   │       ├── controller/
    │   │       │   ├── ManagementController.java       # UI: CRUD for mock endpoints
    │   │       │   └── MockDispatchController.java     # Catches all requests on mock port
    │   │       ├── service/
    │   │       │   ├── MockRegistryService.java        # In-memory store of mock rules
    │   │       │   ├── MockMatcherService.java         # Match incoming request to rule
    │   │       │   └── RequestLogService.java          # Log incoming requests
    │   │       └── model/
    │   │           ├── MockEndpoint.java               # Method + path + response config
    │   │           └── RequestLog.java                 # Logged incoming request
    │   └── resources/
    │       ├── templates/
    │       │   ├── index.html                          # Endpoint list + add form
    │       │   └── logs.html                           # Request log view
    │       ├── static/css/style.css
    │       └── application.properties
    └── test/
        └── java/
            └── com/tools/mockserver/
                ├── MockMatcherServiceTest.java
                └── MockRegistryServiceTest.java
```

## Status
- [ ] Project scaffold
- [ ] Mock endpoint CRUD (UI)
- [ ] In-memory endpoint registry
- [ ] Request matching (method + path)
- [ ] Return configured response
- [ ] Request logging
- [ ] Save/load config to JSON file
- [ ] Response delay support
