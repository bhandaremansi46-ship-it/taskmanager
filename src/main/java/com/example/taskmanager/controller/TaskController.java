package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.TaskService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {

    @Autowired
    private TaskService service;

    // DASHBOARD
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, HttpServletResponse response, Model model) {

        // Prevent browser caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        List<Task> tasks = service.getTasks(user);
        model.addAttribute("tasks", tasks);

        return "dashboard.html"; // your HTML page
    }

    // ADD TASK
    @PostMapping("/task")
    public String addTask(HttpSession session,
                          @RequestParam String title) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        service.addTask(title, user);
        return "redirect:/dashboard";
    }

    // DELETE TASK
    @GetMapping("/delete")
    public String deleteTask(@RequestParam Long id,
                             HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        service.deleteTask(id);
        return "redirect:/dashboard";
    }

    // UPDATE STATUS
    @PostMapping("/update")
    public String updateTask(@RequestParam Long id,
                             @RequestParam String status,
                             HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/";

        service.updateStatus(id, status);
        return "redirect:/dashboard";
    }
}
