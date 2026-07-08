package com.example.knot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.example.knot.entity.AppUser;
import com.example.knot.service.AuthService;
import com.example.knot.service.JwtService;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService,
                          JwtService jwtService,
                          AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AppUser user) {
        try{
            AppUser users=authService.saveData(user);
            return ResponseEntity.ok(users);
        }
        catch(Exception e)
        {
            Map<String,String> errstmt=new HashMap<>();
            if(e.getMessage()!=null  &&  e.getMessage().toLowerCase().contains("duplicate")){
                errstmt.put("message","Email id already registered");
            }
            else
            {
                errstmt.put("message", "Registration failed!."+e.getMessage());
            }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errstmt);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AppUser user) {

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()));

            String token = jwtService.generateToken(user.getEmail());

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Login Successful");
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid Email or Password");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }
    }

    @GetMapping("/get")
    //@PreAuthorize("hasRole('FACILITATOR')","hasRole('CONTRIBUTOR')")
    public List<AppUser> getAllUsers() {
        return authService.getAllData();
    }

    @GetMapping("/get/{id}")
    public AppUser getUser(@PathVariable Long id) {
        return authService.getUserDetails(id);
    }

    @PutMapping("/put/{id}")
    public AppUser updateUser(@PathVariable Long id,
                              @RequestBody AppUser user) {
        return authService.updateDatabase(id, user);
    }

    @DeleteMapping("/delete/{id}")
    public AppUser deleteUser(@PathVariable Long id) {
        return authService.getDelete(id);
    }

    @PatchMapping("/patch/{id}")
    public AppUser patchUser(@PathVariable Long id,
                             @RequestBody AppUser user) {
        return authService.patchUser(id, user);
    }
}
