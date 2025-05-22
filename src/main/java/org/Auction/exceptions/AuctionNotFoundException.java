package org.Auction.exceptions;

public class AuctionNotFoundException extends RuntimeException {
    public AuctionNotFoundException(String message) {
        super(message);
    }
}
