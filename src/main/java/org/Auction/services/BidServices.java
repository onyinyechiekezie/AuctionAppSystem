package org.Auction.services;

public interface BidServices {
    void placeBid(Long auctionId, String bidderId, Double amount);
}
