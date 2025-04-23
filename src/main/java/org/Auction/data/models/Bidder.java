package org.Auction.data.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "bidders")
@EqualsAndHashCode(callSuper = true)
public class Bidder extends User{

    private String paymentMethod;
    private List<String> bidHistory;
}
