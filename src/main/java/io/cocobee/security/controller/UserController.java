package io.cocobee.security.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.cocobee.security.model.User;
import io.cocobee.security.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<User> listUser(){
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public User createI(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/user/{username}")
    public String delete(@PathVariable String username){
        return userService.delete(username);
    }
}
