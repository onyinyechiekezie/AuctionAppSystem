package org.Auction.data.repositories;

import jakarta.validation.constraints.Email;
import org.Auction.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    //Optional<User> findByUsername(String username);
    User findByEmail(String email);


}
