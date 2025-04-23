package org.Auction.data.repositories;

import org.Auction.data.models.Bidder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidderRepository extends MongoRepository<Bidder, String> {
}
