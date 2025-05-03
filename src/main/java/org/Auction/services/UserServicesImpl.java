package org.Auction.services;

import lombok.RequiredArgsConstructor;
import org.Auction.data.models.User;
import org.Auction.data.repositories.UserRepository;
import org.Auction.dto.request.user.LoginUserRequest;
import org.Auction.dto.request.user.RegisterUserRequest;
import org.Auction.exceptions.EmailAlreadyExistsException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.Auction.mappers.UserMapper;

import static org.Auction.mappers.UserMapper.mapToUser;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserRequest request) {
        if (request.getPassword() == null || request.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new EmailAlreadyExistsException("A user with this email already exists");
        }
        User user = mapToUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);

    }
    @Override
    public User loginUser(LoginUserRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new BadCredentialsException("Invalid email or password");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }
}

