package org.Auction.dto.response.auction;

import lombok.Data;
import org.Auction.data.enums.AuctionStatus;

import java.time.LocalDateTime;

@Data
public class CreateAuctionResponse {

    private String id;
    private String itemName;
    private String description;
    private double startingPrice;
    private double currentPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String sellerId;
    private AuctionStatus status;
}
