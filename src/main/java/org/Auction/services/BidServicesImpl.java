package org.Auction.services;

import lombok.RequiredArgsConstructor;
import org.Auction.data.enums.AuctionStatus;
import org.Auction.data.models.Auction;
import org.Auction.data.models.Bid;
import org.Auction.data.repositories.AuctionRepository;
import org.Auction.data.repositories.BidRepository;
import org.Auction.dto.request.bid.BidRequest;
import org.Auction.exceptions.AuctionNotFoundException;
import org.Auction.mappers.BidMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidServicesImpl implements BidServices {
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;
    private final BidMapper bidMapper;


    @Override
    public void placeBid(BidRequest bidRequest, String bidderId) {
        Long auctionId = Long.valueOf(bidRequest.getAuctionId());

//        if (auctionId == null) throw new IllegalArgumentException("Auction ID cannot be null");
        if (bidderId == null || bidderId.trim().isEmpty()) throw new IllegalArgumentException("Bidder ID cannot be empty");
//        if (bidRequest.getAmount() == null || bidRequest.getAmount() <= 0) throw new IllegalArgumentException("Bid must be positive");

        Auction auction = auctionRepository.findAuctionsById(String.valueOf(auctionId));
        if (auction == null) throw new AuctionNotFoundException("Auction not found with ID: " + auctionId);

        if (auction.getStatus() != AuctionStatus.ACTIVE) throw new IllegalStateException("Auction is not active");
        if (auction.getEndTime().isBefore(LocalDateTime.now())) throw new IllegalStateException("Auction has ended");

        if (bidRequest.getAmount() < auction.getStartingPrice())
            throw new IllegalArgumentException("Bid must be at least the starting price: " + auction.getStartingPrice());

        List<Bid> bids = bidRepository.findByAuctionId(String.valueOf(auctionId));
        double highestBid = bids.stream().mapToDouble(Bid::getAmount).max().orElse(0);
        if (bidRequest.getAmount() <= highestBid)
            throw new IllegalArgumentException("Bid must be higher than the current highest bid: " + highestBid);

        Bid bid = bidMapper.mapToBid(bidRequest);
        bid.setBidderId(bidderId);
        bid.setBidTime(LocalDateTime.now());

        bidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidsForAuction(String auctionId) {
        return bidRepository.findByAuctionId(auctionId);
    }

    @Override
    public Optional<Bid> getHighestBid(String auctionId) {
        List<Bid> bids = bidRepository.findByAuctionId(auctionId);
        return bids.stream().max(Comparator.comparingDouble(Bid::getAmount));
    }

    @Override
    public List<Bid> getBidsByBidder(String bidderId) {
        return bidRepository.findByBidderId(bidderId);
    }

    @Override
    public Bid determineWinningBid(String auctionId) {
        return getHighestBid(auctionId)
                .orElseThrow(() -> new AuctionNotFoundException("No winning bid found for auction ID: " + auctionId));
    }

    @Override
    public void deleteBidsForAuction(String auctionId) {
        List<Bid> bids = bidRepository.findByAuctionId(auctionId);
        bidRepository.deleteAll(bids);
    }

    @Override
    public void cancelBid(String bidId, String bidderId) {
        Optional<Bid> optionalBid = bidRepository.findById(bidId);
        if (optionalBid.isEmpty()) {
            throw new IllegalArgumentException("Bid not found with ID: " + bidId);
        }

        Bid bid = optionalBid.get();
        if (!bid.getBidderId().equals(bidderId)) {
            throw new IllegalStateException("You are not authorized to cancel this bid");
        }

        bidRepository.delete(bid);
    }

    @Override
    public Optional<String> getWinningBidder(String auctionId) {
        return bidRepository.findByAuctionId(auctionId).stream()
                .max(Comparator.comparingDouble(Bid::getAmount))
                .map(Bid::getBidderId);
    }


//    @Override
//    public Optional<String> getWinningBidder(String auctionId) {
//        List<Bid> bids = bidRepository.findByAuctionId(auctionId);
//
//        if (bids.isEmpty()) {
//            return Optional.empty();
//        }
//
//        Bid highestBid = bids.get(0);
//        for (Bid bid : bids) {
//            if (bid.getAmount() > highestBid.getAmount()) {
//                highestBid = bid;
//            }
//        }
//
//        return Optional.ofNullable(highestBid.getBidderId());






}
