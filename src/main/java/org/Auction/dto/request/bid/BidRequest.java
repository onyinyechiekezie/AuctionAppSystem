package org.Auction.dto.request.bid;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
public class BidRequest {

    @Positive(message = "Bid amount must be positive")
    private double amount;
    @DBRef
    private String auctionId;
    private LocalDateTime bidTime;


}
