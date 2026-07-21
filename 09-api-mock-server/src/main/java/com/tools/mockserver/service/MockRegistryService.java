package com.tools.mockserver.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tools.mockserver.model.MockEndpoint;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MockRegistryService {

    private final CopyOnWriteArrayList<MockEndpoint> endpoints = new CopyOnWriteArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String CONFIG_FILE = "mock-config.json";

    public List<MockEndpoint> getAll() {
        return new ArrayList<>(endpoints);
    }

    public MockEndpoint add(MockEndpoint endpoint) {
        endpoints.add(endpoint);
        return endpoint;
    }

    public boolean delete(String id) {
        return endpoints.removeIf(e -> e.getId().equals(id));
    }

    public Optional<MockEndpoint> findById(String id) {
        return endpoints.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    public void saveToFile() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File(CONFIG_FILE), new ArrayList<>(endpoints));
    }

    public void loadFromFile() throws IOException {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) {
            throw new IOException("Config file not found: " + CONFIG_FILE);
        }
        List<MockEndpoint> loaded = objectMapper.readValue(
                file, new TypeReference<List<MockEndpoint>>() {});
        endpoints.clear();
        endpoints.addAll(loaded);
    }

    public void clear() {
        endpoints.clear();
    }
}
