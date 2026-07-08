package com.example.knot.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.knot.entity.AppUser;
import com.example.knot.exception.ResourceNotFoundException;
import com.example.knot.repository.AppUserRepository;

@Service
public class AuthService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser saveData(AppUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public List<AppUser> getAllData() {

        return repository.findAll();
    }


    public AppUser getUserDetails(Long id) {

        return repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));
    }

    public AppUser updateDatabase(Long id, AppUser data) {

        AppUser user = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));

        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setDomainRole(data.getDomainRole());
        return repository.save(user);
    }

    public AppUser getDelete(Long id) {

        AppUser user = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));

        repository.delete(user);

        return user;
    }

    public AppUser patchUser(Long id, AppUser data) {

        AppUser user = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("User not found"));

        if (data.getUsername() != null) {
            user.setUsername(data.getUsername());
        }

        if (data.getEmail() != null) {
            user.setEmail(data.getEmail());
        }

        if (data.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(data.getPassword()));
        }

        if (data.getDomainRole() != null) {
            user.setDomainRole(data.getDomainRole());
        }

        return repository.save(user);
    }
}