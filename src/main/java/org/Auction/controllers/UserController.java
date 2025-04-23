package org.Auction.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.Auction.dto.request.user.RegisterUserRequest;
import org.Auction.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterUserRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }


}
