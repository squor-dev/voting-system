package com.example.votingsystem.service.impl;

import com.example.votingsystem.entity.AppUser;
import com.example.votingsystem.repository.AppUserRepository;
import com.example.votingsystem.service.AppUserService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public AppUser createUser(AppUser user) {
        // ensure id is null so we always INSERT
        user.setId(null);
        return appUserRepository.save(user);
    }
}