package org.Auction.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Document(collection = "sellers")
@EqualsAndHashCode(callSuper = true)
public class Seller extends User{

    private String businessName;
    private List<String> auctionIds;
}
