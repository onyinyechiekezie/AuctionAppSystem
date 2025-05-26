package org.Auction.data.repositories;

import org.Auction.data.enums.AuctionStatus;
import org.Auction.data.models.Auction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends MongoRepository<Auction, String> {
    List<Auction> findBySellerId(String sellerId);
    List<Auction> findByStatus(AuctionStatus status);

    Auction findAuctionsById(String auctionId);

    List<Auction> findByWinnerId(String bidderId);
}

