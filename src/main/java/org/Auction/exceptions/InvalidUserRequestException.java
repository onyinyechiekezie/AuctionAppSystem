package org.Auction.exceptions;

public class InvalidUserRequestException extends RuntimeException {

    public InvalidUserRequestException(String message) {
        super(message);
    }
}
