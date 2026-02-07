Task Manager Application

A simple Spring Boot web application for managing personal tasks. Users can register, login, add tasks, update task status, and delete tasks. Built with Spring Boot, Thymeleaf, Spring Data JPA, and H2/MySQL database.

Features

User Authentication: Register and login with email and password.

Task Management:

Add new tasks

Update task status (Pending / Done)

Delete tasks

User-Specific Tasks: Each user only sees their own tasks.

Session Management: Users must login to access the dashboard.

Browser Cache Control: Prevents accessing dashboard after logout.

Technologies Used

Java 21

Spring Boot 

Spring MVC

Spring Data JPA

Thymeleaf

H2 Database / MySQL (configurable)

BCrypt for password encryption

HTML, CSS for frontend
