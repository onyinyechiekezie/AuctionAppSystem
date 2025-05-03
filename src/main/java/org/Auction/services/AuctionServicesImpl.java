package org.Auction.services;

import lombok.RequiredArgsConstructor;
import org.Auction.data.models.Auction;
import org.Auction.data.repositories.AuctionRepository;
import org.Auction.dto.request.auction.AuctionRequest;
import org.Auction.dto.response.auction.AuctionResponse;
import org.Auction.exceptions.InvalidAuctionRequestException;
import org.Auction.mappers.AuctionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionServicesImpl implements AuctionServices {

    private final AuctionRepository auctionRepository;
    private final AuctionMapper auctionMapper;
    //private final AuctionValidator auctionValidator;
    @Override
    public Auction createAuction(AuctionRequest auctionRequest, String sellerId) {
        //auctionValidator.validate(auctionDTO);
        Auction auction = auctionMapper.mapToAuction(auctionRequest);
        auction.setSellerId(sellerId);
        auction.setActive(true);
        auction = auctionRepository.save(auction);
        //return auctionMapper.toDTO(auction);
        return auction;
    }

    public void removeAuction(Auction auction) {

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
    public List<AuctionResponse> getActiveAuctions() {
        List<Auction> auctions = auctionRepository.findByActiveTrue();
        List<AuctionResponse> responses = new ArrayList<>();
        for (Auction auction : auctions) {
            responses.add(auctionMapper.toDTO(auction));
        }
        return responses;
    }

    public List<Auction> getAuctionsBySeller(String sellerId) {
        return auctionRepository.findBySellerId(sellerId).stream()
                .map(auctionMapper::toDTO)
                .collect(Collectors.toList());
    }
}
