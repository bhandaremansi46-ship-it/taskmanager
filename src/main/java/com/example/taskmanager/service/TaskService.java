package com.example.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repo;

    public List<Task> getTasks(User user) {
        return repo.findByUser(user);
    }

    public void addTask(String title, User user) {
        Task t = new Task();
        t.setTitle(title);
        t.setStatus("Pending");
        t.setUser(user);
        repo.save(t);
    }

    public void deleteTask(Long id) {
        repo.deleteById(id);
    }

    public void updateStatus(Long id, String status) {
        repo.findById(id).ifPresent(t -> {
            t.setStatus(status);
            repo.save(t);
        });
    }
}
