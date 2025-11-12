package com.example.tasktracker.service;
import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Async;
import com.example.tasktracker.model.Task;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    // Use ConcurrentHashMap for thread-safe stats tracking
    private final AtomicInteger completedCount = new AtomicInteger(0);

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }
    
    public Task add(Task t) {
        t.setId(UUID.randomUUID().toString());
        tasks.add(t);
        if (t.isCompleted()) {
            // Efficiently track completed count without storing all completed tasks
            completedCount.incrementAndGet();
        }
        return t;
    }
    
    @Async
    public CompletableFuture<Map<String, Object>> calculateStats() {
        // Async calculation to prevent blocking API responses
        int total = tasks.size();
        int completed = completedCount.get();
        
        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("completed", completed);
        return CompletableFuture.completedFuture(res);
    }
}
