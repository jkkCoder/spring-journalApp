package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepostioryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SentimentAnalaysisService {



    public String getSentiment(String text) {
        return "this is sentiment";
    }
}
