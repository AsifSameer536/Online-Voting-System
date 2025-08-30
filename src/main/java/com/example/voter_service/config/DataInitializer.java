package com.example.voter_service.config;

import com.example.voter_service.entity.Role;
import com.example.voter_service.entity.UserAccount;
import com.example.voter_service.repository.RoleRepository;
import com.example.voter_service.repository.UserAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(RoleRepository roles, UserAccountRepository users, PasswordEncoder encoder) {
        return args -> {
            // Ensure roles exist
            Role adminRole = roles.findByName("ROLE_ADMIN").orElseGet(() -> roles.save(new Role("ROLE_ADMIN")));
            Role voterRole = roles.findByName("ROLE_VOTER").orElseGet(() -> roles.save(new Role("ROLE_VOTER")));

            // Admin user
            if (!users.existsByUsername("admin")) {
                UserAccount admin = new UserAccount();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.addRole(adminRole);
                users.save(admin);
            }

            // Test voter user
            if (!users.existsByUsername("voter1")) {
                UserAccount voter = new UserAccount();
                voter.setUsername("voter1");
                voter.setPassword(encoder.encode("voter123"));
                voter.addRole(voterRole);
                users.save(voter);
            }
        };
    }
}
