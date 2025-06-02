package org.Auction.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.Auction.data.models.Auction;
import org.Auction.dto.request.auction.CreateAuctionRequest;
import org.Auction.dto.response.auction.CreateAuctionResponse;
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
    public ResponseEntity<?> createAuction(@RequestBody @Valid CreateAuctionRequest request) {
        try {
            Auction auction = auctionServices.createAuction(request, request.getSellerUsername());
            return ResponseEntity.ok("Auction successfully created");
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
    public ResponseEntity<List<CreateAuctionResponse>> getAuctionsBySeller(@RequestParam String sellerId) {
        List<CreateAuctionResponse> auctions = auctionServices.getAuctionsBySeller(sellerId);
        return ResponseEntity.ok(auctions);
    }

    // Get all active auctions
    @GetMapping("/active")
    public ResponseEntity<List<CreateAuctionResponse>> getActiveAuctions() {
        List<CreateAuctionResponse> activeAuctions = auctionServices.getActiveAuctions();
        return ResponseEntity.ok(activeAuctions);
    }

    // Get all auctions won by a bidder
    @GetMapping("/won")
    public ResponseEntity<List<CreateAuctionResponse>> getAuctionsWon(@RequestParam String bidderId) {
        List<CreateAuctionResponse> wonAuctions = auctionServices.getAuctionsWon(bidderId);
        return ResponseEntity.ok(wonAuctions);
    }
}
