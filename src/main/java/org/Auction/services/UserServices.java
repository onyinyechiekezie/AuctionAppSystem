package org.Auction.services;

import org.Auction.data.enums.UserRole;
import org.Auction.data.models.User;
import org.Auction.dto.request.user.RegisterUserRequest;

public interface UserServices {

    User registerUser(RegisterUserRequest request);
}
