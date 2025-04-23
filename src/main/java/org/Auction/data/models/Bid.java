package org.Auction.data.models;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "bids")
public class Bid {
    @Id
    private String id;
    @Positive
    private double amount;
    @DBRef
    private User bidder;

    private LocalDateTime bidTime = LocalDateTime.now();
    private String auctionId;
}
