package com.example.votingsystem.service;

import com.example.votingsystem.entity.AppUser;
import java.util.List;

public interface AppUserService {
    List<AppUser> getAllUsers();
    AppUser createUser(AppUser user);
}
