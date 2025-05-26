package org.Auction.mappers;

import org.Auction.data.models.Bid;
import org.Auction.dto.request.bid.BidRequest;
import org.Auction.exceptions.InvalidBidRequestException;
import org.springframework.stereotype.Component;

@Component
public class BidMapper {

    public Bid mapToBid(BidRequest bidRequest) {
        if (bidRequest == null) {
            throw new InvalidBidRequestException("Invalid bid request");
        }

        Bid bid = new Bid();
        bid.setAuctionId(bidRequest.getAuctionId());
        bid.setAmount(bidRequest.getAmount());
        bid.setBidTime(bidRequest.getBidTime());
//        bid.setBidderId(bidRequest.getBidderId());
        return bid;
    }
}

