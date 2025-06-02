package org.Auction.dto.request.auction;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
public class CreateAuctionRequest {
//    @Id
//    private String id;

    @NotBlank(message = "Item name is required")
    @Size(min = 5, max = 100)
    private String itemName;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000)
    private String description;

    @Positive(message = "Starting price must be positive")
    private double startingPrice;
    @Future
    private LocalDateTime startTime;
    @Future
    private LocalDateTime endTime;
    @NotBlank(message = "Username is required")
    private String sellerUsername;
//    private String sellerId;
}
