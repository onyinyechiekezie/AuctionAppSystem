package org.Auction.data.repositories;

import org.Auction.data.models.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends MongoRepository<Bid, String> {
    List<Bid> findByAuctionId(String auctionId);
}
