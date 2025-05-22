package org.Auction.mappers;

import org.Auction.data.enums.AuctionStatus;
import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;
import org.Auction.exceptions.InvalidAuctionRequestException;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {

    public Auction mapToAuction(AuctionRequest auctionRequest) {
        if (auctionRequest == null) {
            throw new InvalidAuctionRequestException("Invalid Auction Request");
        }

        Auction auction = new Auction();
        auction.setId(auctionRequest.getId());
        auction.setItemName(auctionRequest.getItemName());
        auction.setDescription(auctionRequest.getDescription());
        auction.setStartingPrice(auctionRequest.getStartingPrice());
        auction.setStartTime(auctionRequest.getStartTime());
        auction.setEndTime(auctionRequest.getEndTime());
        auction.setSellerId(auctionRequest.getSellerUsername());
        auction.setStatus(AuctionStatus.ACTIVE);
        return auction;
    }

    public AuctionResponse toDTO(Auction auction) {
        if (auction == null) {
            return null;
        }

        AuctionResponse response = new AuctionResponse();
        response.setId(auction.getId());
        response.setItemName(auction.getItemName());
        response.setDescription(auction.getDescription());
        response.setStartingPrice(auction.getStartingPrice());
        response.setStartTime(auction.getStartTime());
        response.setEndTime(auction.getEndTime());
        response.setSellerId(auction.getSellerId());
        response.setStatus(auction.getStatus());
        return response;
    }
}

