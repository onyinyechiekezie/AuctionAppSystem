package org.Auction.services;

import lombok.RequiredArgsConstructor;
import org.Auction.data.models.User;
import org.Auction.data.repositories.UserRepository;
import org.Auction.dto.request.user.RegisterUserRequest;
import org.Auction.exceptions.EmailAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServicesImpl implements UserServices{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(RegisterUserRequest request) {
        if (userRepository.existsByEmail((request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }

        User user = new User();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(request.role());

        userRepository.save(user);
    }
}
