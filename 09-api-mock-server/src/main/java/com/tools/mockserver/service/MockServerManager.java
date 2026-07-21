package com.tools.mockserver.service;

import com.sun.net.httpserver.HttpServer;
import com.tools.mockserver.handler.MockDispatchHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

@Service
public class MockServerManager {

    private static final Logger log = LoggerFactory.getLogger(MockServerManager.class);

    private HttpServer mockServer;
    private int currentPort = -1;
    private final MockDispatchHandler dispatchHandler;

    public MockServerManager(MockDispatchHandler dispatchHandler) {
        this.dispatchHandler = dispatchHandler;
    }

    public synchronized void start(int port) throws IOException {
        if (mockServer != null) {
            stop();
        }
        mockServer = HttpServer.create(new InetSocketAddress(port), 0);
        mockServer.createContext("/", dispatchHandler);
        mockServer.setExecutor(Executors.newFixedThreadPool(10));
        mockServer.start();
        currentPort = port;
        log.info("Mock server started on port {}", port);
    }

    public synchronized void stop() {
        if (mockServer != null) {
            mockServer.stop(0);
            mockServer = null;
            log.info("Mock server stopped (was on port {})", currentPort);
            currentPort = -1;
        }
    }

    public synchronized boolean isRunning() {
        return mockServer != null;
    }

    public synchronized int getPort() {
        return currentPort;
    }
}
