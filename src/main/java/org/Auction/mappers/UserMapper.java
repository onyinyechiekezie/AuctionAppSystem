package org.Auction.mappers;

import org.Auction.data.models.User;
import org.Auction.dto.request.user.RegisterUserRequest;
import org.Auction.exceptions.InvalidUserRequestException;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User mapToUser(RegisterUserRequest registerRequest) {
        if (registerRequest == null) {
            throw new InvalidUserRequestException("Registration data is required");
        }

        if (registerRequest.getEmail() == null || registerRequest.getPassword() == null) {
            throw new InvalidUserRequestException("Email and password are required");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
//        user.setPassword(BCryptPasswordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());

        return user;
    }

}
