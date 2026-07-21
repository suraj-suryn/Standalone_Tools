package com.tools.mockserver.service;

import com.tools.mockserver.model.MockEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.List;
import java.util.Optional;

@Service
public class MockMatcherService {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final MockRegistryService registryService;

    public MockMatcherService(MockRegistryService registryService) {
        this.registryService = registryService;
    }

    /**
     * Returns the first rule matching the given HTTP method and path.
     * Path matching uses Ant-style patterns: * (single segment), ** (any depth).
     */
    public Optional<MockEndpoint> match(String method, String path) {
        List<MockEndpoint> endpoints = registryService.getAll();
        return endpoints.stream()
                .filter(e -> e.getMethod() != null && e.getMethod().equalsIgnoreCase(method))
                .filter(e -> e.getPath() != null && pathMatcher.match(e.getPath(), path))
                .findFirst();
    }
}
