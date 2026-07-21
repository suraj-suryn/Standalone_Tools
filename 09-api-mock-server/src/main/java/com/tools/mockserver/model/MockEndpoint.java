package com.tools.mockserver.model;

import java.util.UUID;

public class MockEndpoint {

    private String id;
    private String method;
    private String path;
    private int statusCode;
    private String responseBody;
    private String contentType;
    private int delayMs;

    public MockEndpoint() {
        this.id = UUID.randomUUID().toString();
        this.statusCode = 200;
        this.contentType = "application/json";
        this.delayMs = 0;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }

    public String getResponseBody() { return responseBody; }
    public void setResponseBody(String responseBody) { this.responseBody = responseBody; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public int getDelayMs() { return delayMs; }
    public void setDelayMs(int delayMs) { this.delayMs = delayMs; }
}
