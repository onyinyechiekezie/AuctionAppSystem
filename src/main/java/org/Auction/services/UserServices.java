package org.Auction.services;

import org.Auction.data.enums.UserRole;
import org.Auction.data.models.User;
import org.Auction.dto.request.user.LoginUserRequest;
import org.Auction.dto.request.user.RegisterUserRequest;

public interface UserServices {

    User registerUser(RegisterUserRequest request);

    User loginUser(LoginUserRequest loginRequest );

    User findUserByEmail(String email);
}
