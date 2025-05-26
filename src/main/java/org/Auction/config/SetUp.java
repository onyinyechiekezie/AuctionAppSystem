package org.Auction.config;

import lombok.RequiredArgsConstructor;
import org.Auction.data.models.User;
import org.Auction.data.enums.UserRole;
import org.Auction.data.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetUp implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByRole(UserRole.ADMIN) == null) {
            User admin = new User();
            admin.setEmail("admin@admindomain.com");
            admin.setPassword(passwordEncoder.encode("AdminPass123"));
            admin.setRole(UserRole.ADMIN);
            admin.setName("System Admin");

            userRepository.save(admin);
            System.out.println("Default admin user created: admin@admindomain.com");
        } else {
            System.out.println("Admin already exists, skipping creation.");
        }
    }
}
