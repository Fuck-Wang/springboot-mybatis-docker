package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.serviceImp.UserServiceImp;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserServiceImp userServiceImp;

    @GetMapping("/users")
    public List<User> findAll() {
        return userServiceImp.findAll();
    }

    @PostMapping("/users")
    public List<User> saveAll(@RequestBody List<User> users) {
        return userServiceImp.saveAll(users);
    }

}
