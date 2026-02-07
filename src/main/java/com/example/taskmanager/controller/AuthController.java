package com.example.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // LOGIN PAGE
    @GetMapping("/")
    public String loginPage() {
        return "login.html"; // your HTML file
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage() {
        return "register.html";
    }

    // SAVE REGISTER
    @PostMapping("/register")
    public String save(User user, Model model) {
        boolean success = userService.register(user);
        if (!success) {
            model.addAttribute("error", "Email already registered");
            return "register.html";
        }
        return "redirect:/"; // back to login
    }

    // LOGIN CHECK
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        User user = userService.login(email, password);
        if (user == null) {
            model.addAttribute("error", "Invalid email or password");
            return "login.html";
        }

        session.setAttribute("user", user);
        return "redirect:/dashboard";
    }

   
}
