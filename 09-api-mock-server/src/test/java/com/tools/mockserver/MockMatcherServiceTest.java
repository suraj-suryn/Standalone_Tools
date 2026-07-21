package com.tools.mockserver;

import com.tools.mockserver.model.MockEndpoint;
import com.tools.mockserver.service.MockMatcherService;
import com.tools.mockserver.service.MockRegistryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MockMatcherServiceTest {

    private MockRegistryService registry;
    private MockMatcherService matcher;

    @BeforeEach
    void setUp() {
        registry = new MockRegistryService();
        matcher  = new MockMatcherService(registry);
    }

    @Test
    void match_exactPath_shouldMatch() {
        registry.add(endpoint("GET", "/api/users", 200));

        Optional<MockEndpoint> result = matcher.match("GET", "/api/users");

        assertThat(result).isPresent();
        assertThat(result.get().getPath()).isEqualTo("/api/users");
    }

    @Test
    void match_singleWildcard_shouldMatchOneSegment() {
        registry.add(endpoint("GET", "/api/users/*", 200));

        assertThat(matcher.match("GET", "/api/users/123")).isPresent();
        assertThat(matcher.match("GET", "/api/users/abc")).isPresent();
    }

    @Test
    void match_singleWildcard_shouldNotMatchMultipleSegments() {
        registry.add(endpoint("GET", "/api/users/*", 200));

        assertThat(matcher.match("GET", "/api/users/123/orders")).isEmpty();
    }

    @Test
    void match_doubleWildcard_shouldMatchAnyDepth() {
        registry.add(endpoint("GET", "/api/**", 200));

        assertThat(matcher.match("GET", "/api/users")).isPresent();
        assertThat(matcher.match("GET", "/api/users/123")).isPresent();
        assertThat(matcher.match("GET", "/api/users/123/orders")).isPresent();
    }

    @Test
    void match_methodMismatch_shouldNotMatch() {
        registry.add(endpoint("GET", "/api/users", 200));

        assertThat(matcher.match("POST", "/api/users")).isEmpty();
        assertThat(matcher.match("DELETE", "/api/users")).isEmpty();
    }

    @Test
    void match_noRules_shouldReturnEmpty() {
        assertThat(matcher.match("GET", "/anything")).isEmpty();
    }

    @Test
    void match_methodCaseInsensitive_shouldMatch() {
        registry.add(endpoint("GET", "/api/test", 200));

        assertThat(matcher.match("get", "/api/test")).isPresent();
        assertThat(matcher.match("Get", "/api/test")).isPresent();
    }

    @Test
    void match_firstRuleWins_whenMultipleMatch() {
        MockEndpoint first  = endpoint("GET", "/api/**", 200);
        MockEndpoint second = endpoint("GET", "/api/**", 404);
        first.setResponseBody("first");
        second.setResponseBody("second");
        registry.add(first);
        registry.add(second);

        Optional<MockEndpoint> result = matcher.match("GET", "/api/anything");

        assertThat(result).isPresent();
        assertThat(result.get().getResponseBody()).isEqualTo("first");
    }

    private MockEndpoint endpoint(String method, String path, int status) {
        MockEndpoint ep = new MockEndpoint();
        ep.setMethod(method);
        ep.setPath(path);
        ep.setStatusCode(status);
        return ep;
    }
}
