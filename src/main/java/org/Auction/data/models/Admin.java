package org.Auction.data.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "admins")
@EqualsAndHashCode(callSuper = true)
public class Admin extends User{


}
