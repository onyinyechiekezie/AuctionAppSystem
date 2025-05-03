package org.Auction.services;

import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;

import java.util.List;

public interface AuctionServices {

    Auction createAuction(AuctionRequest request, String userId);
//    List<AuctionResponse> getActiveAuctions();
}
