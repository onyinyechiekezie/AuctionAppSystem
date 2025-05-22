package org.Auction.services;

import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;

import java.util.List;

public interface AuctionServices {

    Auction createAuction(AuctionRequest request, String userId);
    void removeAuction(Auction auctionId, String sellerId);

    //    @Override
    //    public List<AuctionResponse> getActiveAuctions() {
    //        return auctionRepository.findByActiveTrue().stream()
    //                .map(auctionMapper::toDTO)
    //                .collect(Collectors.toList());
    //    }
    List<AuctionResponse> getActiveAuctions();

    List<AuctionResponse> getAuctionsBySeller(String sellerId);

    List<AuctionResponse> getAuctionsWon(String bidderId);
//    List<AuctionResponse> getActiveAuctions();
}
