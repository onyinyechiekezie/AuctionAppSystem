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
    @NotBlank
    @Size(min = 5, max = 100)
    private String title;
    @NotBlank
    @Size(min = 10, max = 1000)
    private String description;
    @Positive
    private double startingPrice;
    @PositiveOrZero
    private double currentHighestBid;
    @DBRef
    private User seller;
    @Future
    private LocalDateTime startTime;
    @Future
    private LocalDateTime endTime;
    private boolean isEnded;
    //private String sellerId;
    private boolean isActive;
    private List<Bid> bids;
}
