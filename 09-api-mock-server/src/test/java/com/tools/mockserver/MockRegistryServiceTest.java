package com.tools.mockserver;

import com.tools.mockserver.model.MockEndpoint;
import com.tools.mockserver.service.MockRegistryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MockRegistryServiceTest {

    private MockRegistryService service;

    @BeforeEach
    void setUp() {
        service = new MockRegistryService();
    }

    @Test
    void add_shouldStoreEndpointAndAssignId() {
        MockEndpoint ep = new MockEndpoint();
        ep.setMethod("GET");
        ep.setPath("/api/users");
        ep.setStatusCode(200);

        MockEndpoint saved = service.add(ep);

        assertThat(saved.getId()).isNotNull();
        assertThat(service.getAll()).hasSize(1);
    }

    @Test
    void getAll_shouldReturnAllAddedEndpoints() {
        MockEndpoint ep1 = endpoint("GET",  "/api/users");
        MockEndpoint ep2 = endpoint("POST", "/api/orders");

        service.add(ep1);
        service.add(ep2);

        List<MockEndpoint> all = service.getAll();
        assertThat(all).hasSize(2);
        assertThat(all).extracting(MockEndpoint::getPath)
                .containsExactlyInAnyOrder("/api/users", "/api/orders");
    }

    @Test
    void delete_existingId_shouldRemoveAndReturnTrue() {
        MockEndpoint ep = service.add(endpoint("GET", "/api/test"));
        String id = ep.getId();

        boolean result = service.delete(id);

        assertThat(result).isTrue();
        assertThat(service.getAll()).isEmpty();
    }

    @Test
    void delete_nonExistingId_shouldReturnFalse() {
        boolean result = service.delete("does-not-exist");
        assertThat(result).isFalse();
    }

    @Test
    void clear_shouldRemoveAllEndpoints() {
        service.add(endpoint("GET",  "/api/a"));
        service.add(endpoint("POST", "/api/b"));

        service.clear();

        assertThat(service.getAll()).isEmpty();
    }

    @Test
    void findById_existingId_shouldReturnEndpoint() {
        MockEndpoint ep = service.add(endpoint("GET", "/api/find-me"));

        assertThat(service.findById(ep.getId())).isPresent()
                .get().extracting(MockEndpoint::getPath).isEqualTo("/api/find-me");
    }

    @Test
    void findById_missingId_shouldReturnEmpty() {
        assertThat(service.findById("ghost")).isEmpty();
    }

    private MockEndpoint endpoint(String method, String path) {
        MockEndpoint ep = new MockEndpoint();
        ep.setMethod(method);
        ep.setPath(path);
        return ep;
    }
}
