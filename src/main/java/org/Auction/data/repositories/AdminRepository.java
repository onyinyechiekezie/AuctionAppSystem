package org.Auction.data.repositories;

import org.Auction.data.models.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {
    //List<Auction> findBySellerIdAndIsActive(String sellerId, boolean isActive);
    //List<Auction> findByEndTimeAfter(LocalDateTime time);
}
