package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepostioryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class getUserSATests {

    @Autowired
    private UserRepostioryImpl userRepository;

    @Test
    void testSaveNewUser() {
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
