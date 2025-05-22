package org.Auction.services;

import lombok.RequiredArgsConstructor;
import org.Auction.data.models.Auction;
import org.Auction.data.repositories.AuctionRepository;
import org.Auction.data.repositories.BidRepository;
import org.Auction.data.repositories.UserRepository;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;
import org.Auction.exceptions.AuctionNotFoundException;
import org.Auction.exceptions.UnauthorizedException;
import org.Auction.mappers.AuctionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.Auction.data.enums.AuctionStatus.ACTIVE;

@Service
@RequiredArgsConstructor
public class AuctionServicesImpl implements AuctionServices {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;
    private final BidRepository bidRepository;
    private final UserRepository userRepository;
    //private final AuctionValidator auctionValidator;
    @Override
    public Auction createAuction(AuctionRequest auctionRequest, String sellerId) {
        //auctionValidator.validate(auctionDTO);
        Auction auction = auctionMapper.mapToAuction(auctionRequest);
        auction.setSellerId(sellerId);
        auction.setStatus(ACTIVE);
        auction = auctionRepository.save(auction);
        //return auctionMapper.toDTO(auction);
        return auction;
    }
    @Override
    public void removeAuction(Auction auctionId, String sellerId) {
        auctionRepository.findById(String.valueOf(auctionId));
        if (auctionRepository.findBySellerId(sellerId) == null || sellerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller ID cannot be empty");
        }

        Auction auction = (Auction) auctionRepository.findAuctionsById(String.valueOf(auctionId));
        if (auction == null) {
            throw new AuctionNotFoundException("Auction not found with ID: " + auctionId);
        }

        if (!auction.getSellerId().equals(sellerId)) {
            throw new UnauthorizedException("User is not authorized to delete this auction");
        }

        if (bidRepository.existsByAuctionId(String.valueOf(auctionId))) {
            throw new IllegalStateException("Cannot delete an auction with bids");
        }

        auctionRepository.delete(auction);

    }



//    public Auction getAuctionById(String id) {
//        Auction auction = auctionRepository.findById(id)
//                .orElseThrow(() -> new InvalidAuctionRequestException("Auction not found"));
//        return auctionMapper.toDTO(auction);
//    }


//    @Override
//    public List<AuctionResponse> getActiveAuctions() {
//        return auctionRepository.findByActiveTrue().stream()
//                .map(auctionMapper::toDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<AuctionResponse> getAuctionsBySeller(String sellerId) {
        if (sellerId == null || sellerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Seller ID cannot be empty");
        }
        if (!userRepository.existsById(sellerId)) {
            throw new IllegalArgumentException("Seller not found with ID: " + sellerId);
        }
        List<Auction> auctions = auctionRepository.findBySellerId(sellerId);
        List<AuctionResponse> auctionResponses = new ArrayList<>();
        for (Auction auction : auctions) {
            AuctionResponse response = auctionMapper.toDTO(auction);
            auctionResponses.add(response);
        }
        return auctionResponses;
    }

    @Override
    public List<AuctionResponse> getActiveAuctions() {
        List<Auction> activeAuctions = auctionRepository.findByActiveTrue();
        List<AuctionResponse> auctionResponses = new ArrayList<>();

        for (Auction auction : activeAuctions) {
            AuctionResponse response = auctionMapper.toDTO(auction);
            auctionResponses.add(response);
        }
        return auctionResponses;
    }

    @Override
    public List<AuctionResponse> getAuctionsWon(String bidderId) {
        if (bidderId == null || bidderId.trim().isEmpty()) {
            throw new IllegalArgumentException("Bidder ID cannot be null or empty");
        }
        List<Auction> wonAuctions = auctionRepository.findByWinnerId(bidderId);
        List<AuctionResponse> auctionResponses = new ArrayList<>();
        for (Auction auction : wonAuctions) {
            AuctionResponse response = auctionMapper.toDTO(auction);
            auctionResponses.add(response);
        }
        return auctionResponses;
    }
}
