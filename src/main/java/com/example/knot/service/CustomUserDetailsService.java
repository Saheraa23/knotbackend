package com.example.knot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.knot.entity.AppUser;
import com.example.knot.exception.ResourceNotFoundException;
import com.example.knot.repository.AppUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = repo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getDomainRole().name())
                .build();
    }
}