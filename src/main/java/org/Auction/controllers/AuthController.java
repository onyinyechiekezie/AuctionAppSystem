package org.Auction.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.Auction.data.models.User;
import org.Auction.dto.request.user.LoginUserRequest;
import org.Auction.dto.request.user.RegisterUserRequest;
import org.Auction.services.AuthServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {


    private final AuthServices authServices;

    @PermitAll
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        try {
            authServices.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody @Valid LoginUserRequest loginRequest) {
        try{
            User user = authServices.loginUser(loginRequest);
            return ResponseEntity.ok("Login successful. Role: " + user.getRole());
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }





}
