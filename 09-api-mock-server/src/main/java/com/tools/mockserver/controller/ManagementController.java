package com.tools.mockserver.controller;

import com.tools.mockserver.model.MockEndpoint;
import com.tools.mockserver.model.RequestLog;
import com.tools.mockserver.service.MockRegistryService;
import com.tools.mockserver.service.MockServerManager;
import com.tools.mockserver.service.RequestLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ManagementController {

    private final MockRegistryService registryService;
    private final MockServerManager serverManager;
    private final RequestLogService logService;

    public ManagementController(MockRegistryService registryService,
                                 MockServerManager serverManager,
                                 RequestLogService logService) {
        this.registryService = registryService;
        this.serverManager = serverManager;
        this.logService = logService;
    }

    // ── Mock Rule CRUD ──────────────────────────────────────────────

    @GetMapping("/mocks")
    public List<MockEndpoint> listMocks() {
        return registryService.getAll();
    }

    @PostMapping("/mocks")
    public ResponseEntity<MockEndpoint> addMock(@RequestBody MockEndpoint endpoint) {
        if (endpoint.getMethod() == null || endpoint.getPath() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(registryService.add(endpoint));
    }

    @DeleteMapping("/mocks/{id}")
    public ResponseEntity<Void> deleteMock(@PathVariable String id) {
        return registryService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/mocks")
    public Map<String, Object> clearAllMocks() {
        registryService.clear();
        Map<String, Object> result = new HashMap<>();
        result.put("cleared", true);
        return result;
    }

    // ── Mock Server Lifecycle ────────────────────────────────────────

    @GetMapping("/server/status")
    public Map<String, Object> serverStatus() {
        Map<String, Object> result = new HashMap<>();
        result.put("running", serverManager.isRunning());
        result.put("port", serverManager.getPort());
        return result;
    }

    @PostMapping("/server/start")
    public ResponseEntity<Map<String, Object>> startServer(@RequestBody Map<String, Integer> body) {
        int port = body.containsKey("port") ? body.get("port") : 9000;
        if (port < 1024 || port > 65535) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", "Port must be between 1024 and 65535");
            return ResponseEntity.badRequest().body(err);
        }
        try {
            serverManager.start(port);
            Map<String, Object> ok = new HashMap<>();
            ok.put("started", true);
            ok.put("port", port);
            return ResponseEntity.ok(ok);
        } catch (IOException e) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", "Failed to start: " + e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }

    @PostMapping("/server/stop")
    public Map<String, Object> stopServer() {
        serverManager.stop();
        Map<String, Object> result = new HashMap<>();
        result.put("stopped", true);
        return result;
    }

    // ── Request Logs ────────────────────────────────────────────────

    @GetMapping("/logs")
    public List<RequestLog> getLogs() {
        return logService.getAll();
    }

    @DeleteMapping("/logs")
    public Map<String, Object> clearLogs() {
        logService.clear();
        Map<String, Object> result = new HashMap<>();
        result.put("cleared", true);
        return result;
    }

    // ── Config Persistence ──────────────────────────────────────────

    @PostMapping("/config/save")
    public ResponseEntity<Map<String, Object>> saveConfig() {
        try {
            registryService.saveToFile();
            Map<String, Object> ok = new HashMap<>();
            ok.put("saved", true);
            ok.put("file", "mock-config.json");
            return ResponseEntity.ok(ok);
        } catch (IOException e) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(err);
        }
    }

    @PostMapping("/config/load")
    public ResponseEntity<Map<String, Object>> loadConfig() {
        try {
            registryService.loadFromFile();
            int count = registryService.getAll().size();
            Map<String, Object> ok = new HashMap<>();
            ok.put("loaded", true);
            ok.put("count", count);
            return ResponseEntity.ok(ok);
        } catch (IOException e) {
            Map<String, Object> err = new HashMap<>();
            err.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(err);
        }
    }
}
