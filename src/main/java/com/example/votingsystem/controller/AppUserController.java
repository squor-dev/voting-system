package com.example.votingsystem.controller;

import com.example.votingsystem.entity.AppUser;
import com.example.votingsystem.service.AppUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AppUserController {
    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getUsers() {
        return appUserService.getAllUsers();
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) {
        return appUserService.createUser(user);
    }
}
