package com.tools.mockserver.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.tools.mockserver.model.MockEndpoint;
import com.tools.mockserver.model.RequestLog;
import com.tools.mockserver.service.MockMatcherService;
import com.tools.mockserver.service.RequestLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class MockDispatchHandler implements HttpHandler {

    private static final Logger log = LoggerFactory.getLogger(MockDispatchHandler.class);

    private final MockMatcherService matcherService;
    private final RequestLogService logService;

    public MockDispatchHandler(MockMatcherService matcherService, RequestLogService logService) {
        this.matcherService = matcherService;
        this.logService = logService;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();

        // Handle CORS preflight
        if ("OPTIONS".equalsIgnoreCase(method)) {
            addCorsHeaders(exchange);
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        Optional<MockEndpoint> match = matcherService.match(method, path);

        if (match.isPresent()) {
            MockEndpoint endpoint = match.get();

            if (endpoint.getDelayMs() > 0) {
                try {
                    Thread.sleep(endpoint.getDelayMs());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            String body = endpoint.getResponseBody() != null ? endpoint.getResponseBody() : "";
            byte[] responseBytes = body.getBytes(StandardCharsets.UTF_8);
            String contentType = endpoint.getContentType() != null
                    ? endpoint.getContentType() : "application/json";

            addCorsHeaders(exchange);
            exchange.getResponseHeaders().set("Content-Type", contentType);
            exchange.sendResponseHeaders(endpoint.getStatusCode(), responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }

            logService.log(new RequestLog(method, path, endpoint.getId(), endpoint.getStatusCode()));
            log.info("MATCHED  {} {} → {}", method, path, endpoint.getStatusCode());

        } else {
            String body = "{\"error\":\"No mock rule matched for " + method + " " + path + "\"}";
            byte[] responseBytes = body.getBytes(StandardCharsets.UTF_8);

            addCorsHeaders(exchange);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(404, responseBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(responseBytes);
            }

            logService.log(new RequestLog(method, path, null, 404));
            log.info("NO MATCH {} {}", method, path);
        }
    }

    private void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,PATCH,OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type,Authorization");
    }
}
