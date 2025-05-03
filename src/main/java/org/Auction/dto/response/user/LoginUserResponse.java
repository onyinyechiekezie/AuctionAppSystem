package org.Auction.dto.response.user;

import lombok.Data;
import org.Auction.data.enums.UserRole;

@Data
public class LoginUserResponse {

    private String message;
    private String username;
    private UserRole role;
}
