package org.Auction.data.models;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "auctions")
public class Auction {

    @Id
    private String id;
    private String itemName;
    private String description;
    private double startingPrice;
    @PositiveOrZero
    private double currentHighestBid;
//    @DBRef
//    private User seller;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isEnded;
    private String sellerId;
    private boolean active;
    private List<Bid> bids;
}
