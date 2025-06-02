package org.Auction.services;

import org.Auction.data.models.Bid;
import org.Auction.dto.request.bid.BidRequest;

import java.util.List;
import java.util.Optional;

public interface BidServices {
    void placeBid(BidRequest bidRequest, String bidderId);

    List<Bid> getBidsForAuction(String auctionId);

    Optional<Bid> getHighestBid(String auctionId);

    List<Bid> getBidsByBidder(String bidderId);

    Bid determineWinningBid(String auctionId);

    void deleteBidsForAuction(String auctionId);

    void cancelBid(String bidId, String bidderId);

    Optional<String> getWinningBidder(String auctionId);


//    void placeBid(Long auctionId, String bidderId, Double amount);
}
