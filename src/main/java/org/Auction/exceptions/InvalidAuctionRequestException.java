package org.Auction.exceptions;

public class InvalidAuctionRequestException extends RuntimeException {
    public InvalidAuctionRequestException(String message) {
        super(message);
    }
}
