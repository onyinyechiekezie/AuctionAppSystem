package org.Auction.dto.response.auction;

import java.time.LocalDateTime;

public class AuctionResponse {

    private String id;
    private String title;
    private String description;
    private double startingPrice;
    private double currentPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String sellerId;
    private boolean active;
}
