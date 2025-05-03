package org.Auction.exceptions;

public class InvalidBidRequestException extends RuntimeException {
    public InvalidBidRequestException(String message) {

        super(message);
    }
}
