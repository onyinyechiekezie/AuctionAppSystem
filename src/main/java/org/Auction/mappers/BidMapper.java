package org.Auction.mappers;

import org.Auction.data.models.Bid;
import org.Auction.dto.request.bid.BidRequest;
import org.Auction.exceptions.InvalidBidRequestException;

public class BidMapper {
    public Bid mapToBid(BidRequest bidRequest) {
        if (bidRequest == null) {
            throw new InvalidBidRequestException("Invalid bid request");
        }
        Bid bid = new Bid();
//        bid.setId(bidRequest.getId());
        bid.setAmount(bidRequest.getAmount());
        bid.setBidTime(bidRequest.getBidTime());
        return bid;
    }
}
