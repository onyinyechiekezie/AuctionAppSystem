package org.Auction.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.Auction.data.models.Bid;
import org.Auction.dto.request.bid.BidRequest;
import org.Auction.services.BidServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bids")
@RequiredArgsConstructor
public class BidController {

    private final BidServices bidServices;

    @PostMapping("/place")
    public ResponseEntity<String> placeBid(@RequestBody @Valid BidRequest bidRequest) {
        try {
            String bidderId = bidRequest.getBidderId();
            bidServices.placeBid(bidRequest, bidderId);
            return ResponseEntity.ok("Bid placed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/by-bidder/{bidderId}")
    public ResponseEntity<List<Bid>> getBidsByBidder(@PathVariable String bidderId) {
        List<Bid> bids = bidServices.getBidsByBidder(bidderId);
        return ResponseEntity.ok(bids);
    }

    @GetMapping("/by-auction/{auctionId}")
    public ResponseEntity<List<Bid>> getBidsForAuction(@PathVariable String auctionId) {
        List<Bid> bids = bidServices.getBidsForAuction(auctionId);
        return ResponseEntity.ok(bids);
    }

    @GetMapping("/highest/{auctionId}")
    public ResponseEntity<?> getHighestBid(@PathVariable String auctionId) {
        Optional<Bid> bid = bidServices.getHighestBid(auctionId);
        return bid.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/winner/{auctionId}")
    public ResponseEntity<?> getWinningBidder(@PathVariable String auctionId) {
        Optional<String> winner = bidServices.getWinningBidder(auctionId);
        return winner.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
