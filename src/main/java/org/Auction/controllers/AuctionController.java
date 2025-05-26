package org.Auction.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;
import org.Auction.services.AuctionServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
@RequiredArgsConstructor
public class AuctionController {

    private final AuctionServices auctionServices;

    //Create a new auction
    @PostMapping("/create")
    public ResponseEntity<?> createAuction(@RequestBody @Valid AuctionRequest request,
                                           @RequestParam String sellerId) {
        try {
            Auction auction = auctionServices.createAuction(request, sellerId);
            return ResponseEntity.ok(auction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete an auction (by seller)
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAuction(@RequestParam String auctionId,
                                           @RequestParam String sellerId) {
        try {
            Auction dummyAuction = new Auction();
            dummyAuction.setId(auctionId);
            auctionServices.removeAuction(dummyAuction, sellerId);
            return ResponseEntity.ok("Auction deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get auctions created by a specific seller
    @GetMapping("/by-seller")
    public ResponseEntity<List<AuctionResponse>> getAuctionsBySeller(@RequestParam String sellerId) {
        List<AuctionResponse> auctions = auctionServices.getAuctionsBySeller(sellerId);
        return ResponseEntity.ok(auctions);
    }

    // Get all active auctions
    @GetMapping("/active")
    public ResponseEntity<List<AuctionResponse>> getActiveAuctions() {
        List<AuctionResponse> activeAuctions = auctionServices.getActiveAuctions();
        return ResponseEntity.ok(activeAuctions);
    }

    // Get all auctions won by a bidder
    @GetMapping("/won")
    public ResponseEntity<List<AuctionResponse>> getAuctionsWon(@RequestParam String bidderId) {
        List<AuctionResponse> wonAuctions = auctionServices.getAuctionsWon(bidderId);
        return ResponseEntity.ok(wonAuctions);
    }
}
