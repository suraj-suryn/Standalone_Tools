package com.tools.mockserver.service;

import com.tools.mockserver.model.RequestLog;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

@Service
public class RequestLogService {

    private static final int MAX_LOGS = 100;
    private final Deque<RequestLog> logs = new ArrayDeque<>();

    public synchronized void log(RequestLog entry) {
        if (logs.size() >= MAX_LOGS) {
            logs.pollFirst();
        }
        logs.addLast(entry);
    }

    public synchronized List<RequestLog> getAll() {
        List<RequestLog> result = new ArrayList<>(logs);
        Collections.reverse(result); // most recent first
        return result;
    }

    public synchronized void clear() {
        logs.clear();
    }
}
