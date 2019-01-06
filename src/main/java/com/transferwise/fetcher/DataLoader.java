package com.transferwise.fetcher;

import com.transferwise.fetcher.entity.User;
import com.transferwise.fetcher.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        logger.info("Database population started");

        // users
        storeUsers();
    }

    private void storeUsers() {
        User user1 = new User("Alan", "Partdige", "GBP");
        User user2 = new User("Ron", "Swanson", "USD");

        userRepository.save(user1);
        userRepository.save(user2);
    }

}