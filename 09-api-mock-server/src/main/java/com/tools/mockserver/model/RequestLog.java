package com.tools.mockserver.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestLog {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private String timestamp;
    private String method;
    private String path;
    private String matchedRuleId;
    private int responseStatus;

    public RequestLog(String method, String path, String matchedRuleId, int responseStatus) {
        this.timestamp = LocalDateTime.now().format(FORMATTER);
        this.method = method;
        this.path = path;
        this.matchedRuleId = matchedRuleId;
        this.responseStatus = responseStatus;
    }

    public RequestLog() {}

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }

    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }

    public String getMatchedRuleId() { return matchedRuleId; }
    public void setMatchedRuleId(String matchedRuleId) { this.matchedRuleId = matchedRuleId; }

    public int getResponseStatus() { return responseStatus; }
    public void setResponseStatus(int responseStatus) { this.responseStatus = responseStatus; }
}
