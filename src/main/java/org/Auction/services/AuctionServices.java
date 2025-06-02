package org.Auction.services;

import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.CreateAuctionRequest;
import org.Auction.dto.response.auction.CreateAuctionResponse;

import java.util.List;

public interface AuctionServices {

    Auction createAuction(CreateAuctionRequest request, String userId);
    void removeAuction(Auction auctionId, String sellerId);

    //    @Override
    //    public List<AuctionResponse> getActiveAuctions() {
    //        return auctionRepository.findByActiveTrue().stream()
    //                .map(auctionMapper::toDTO)
    //                .collect(Collectors.toList());
    //    }
    List<CreateAuctionResponse> getActiveAuctions();

    List<CreateAuctionResponse> getAuctionsBySeller(String sellerId);

    List<CreateAuctionResponse> getAuctionsWon(String bidderId);
//    List<AuctionResponse> getActiveAuctions();
}
