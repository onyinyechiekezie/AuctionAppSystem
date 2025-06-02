package org.Auction.dto.request.bid;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;

@Data
public class BidRequest {

    @NotNull(message = "Bidder ID cannot be null")
    private String bidderId;
    @Positive(message = "Bid amount must be positive")
    @NotNull(message = "Bid amount cannot be null")
    @Min(value = 1, message = "Bid amount must be at least 1")
    private double amount;
    @NotNull(message = "Auction ID cannot be null")
    private String auctionId;
    @NotNull(message = "Bid time cannot be null")
    private LocalDateTime bidTime;


}
