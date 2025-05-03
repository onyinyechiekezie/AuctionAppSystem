package org.Auction.mappers;

import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;
import org.Auction.exceptions.InvalidAuctionRequestException;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {

    public Auction mapToAuction(AuctionRequest auctionRequest) {
        Auction auction = new Auction();
        if (auctionRequest == null) {
            throw new InvalidAuctionRequestException("Invalid Auction Request");
        }
        auction.setId(auctionRequest.getId());
        auction.setItemName(auctionRequest.getItemName());
        auction.setDescription(auctionRequest.getDescription());
        auction.setStartingPrice(auctionRequest.getStartingPrice());
        auction.setStartTime(auctionRequest.getStartTime());
        auction.setEndTime(auctionRequest.getEndTime());
        auction.setSellerId(auctionRequest.getSellerUsername());
        //auction.setActive(auctionRequest.get);
        return auction;

    }

    public AuctionResponse toDTO(Auction auction) {
        Auction dto = new Auction();
        dto.setId(auction.getId());
        dto.setItemName(auction.getItemName());
        dto.setDescription(auction.getDescription());
        dto.setStartingPrice(auction.getStartingPrice());
        dto.setStartTime(auction.getStartTime());
        dto.setEndTime(auction.getEndTime());
        dto.setSellerId(auction.getSellerId());
        dto.setActive(auction.isActive());
        return dto;
    }

}
