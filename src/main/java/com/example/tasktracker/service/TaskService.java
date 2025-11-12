package com.example.tasktracker.service;
import org.springframework.stereotype.Service;
import com.example.tasktracker.model.Task;
import java.util.*;

@Service
public class TaskService {
    private List<Task> tasks = new ArrayList<>();
    private List<Task> completedTasks = new ArrayList<>();

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }
    public Task add(Task t) {
        t.setId(UUID.randomUUID().toString());
        tasks.add(t);
        if (t.isCompleted()) {
            // Memory inefficient: completedTasks grows endlessly
            completedTasks.add(t);
        }
        return t;
    }
    public Map<String, Object> calculateStats() {
        // Inefficient: calc done synchronously, iterates over entire list
        int total = tasks.size();
        int completed = 0;
        for (Task t : tasks) {
            if (t.isCompleted()) completed++;
        }
        Map<String, Object> res = new HashMap<>();
        res.put("total", total);
        res.put("completed", completed);
        return res;
    }
}
