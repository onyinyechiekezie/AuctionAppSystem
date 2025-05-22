package org.Auction.services;

import lombok.RequiredArgsConstructor;
import org.Auction.data.enums.AuctionStatus;
import org.Auction.data.models.Auction;
import org.Auction.data.models.Bid;
import org.Auction.data.repositories.AuctionRepository;
import org.Auction.data.repositories.BidRepository;
import org.Auction.exceptions.AuctionNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BidServicesImpl implements BidServices {
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    @Override
    public void placeBid(Long auctionId, String bidderId, Double amount) {
        if (auctionId == null) {
            throw new IllegalArgumentException("Auction ID cannot be null");
        }
        if (bidderId == null || bidderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Bidder ID cannot be null or empty");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Bid amount must be positive");
        }
        if (!auctionRepository.existsById(String.valueOf(auctionId))) {
            throw new AuctionNotFoundException("Auction not found with ID: " + auctionId);
        }
        Auction auction = auctionRepository.findAuctionsById(String.valueOf(auctionId));

        if (auction.getStatus() != AuctionStatus.ACTIVE) {
            throw new IllegalStateException("Cannot bid on an inactive auction");
        }

        if (auction.getEndTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Auction has already ended");
        }

        if (amount < auction.getStartingPrice()) {
            throw new IllegalArgumentException("Bid must be at least the starting price: " + auction.getStartingPrice());
        }

        List<Bid> bids = bidRepository.findByAuctionId(String.valueOf(auctionId));
        double highestBid = 0.0;
        for (Bid bid : bids) {
            if (bid.getAmount() > highestBid) {
                highestBid = bid.getAmount();
            }
        }
        if (!bids.isEmpty() && amount <= highestBid) {
            throw new IllegalArgumentException("Bid must be higher than current highest bid: " + highestBid);
        }
    }
}
