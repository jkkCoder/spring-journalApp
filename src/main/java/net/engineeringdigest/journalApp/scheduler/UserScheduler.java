package net.engineeringdigest.journalApp.scheduler;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepostioryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentAnalaysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler{

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepostioryImpl userRepostiory;

    @Autowired
    SentimentAnalaysisService sentimentAnalaysisService;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUserAndSendMail() {
        List<User> userWithSA = userRepostiory.getUserForSA();
        for(User user: userWithSA) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredJournalEntriesContent = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x-> x.getContent()).collect(Collectors.toList());
            String entry = String.join(", ", filteredJournalEntriesContent);
            String sentiment = sentimentAnalaysisService.getSentiment(entry);
            emailService.sendMail(user.getEmail(), "Sentiment for last 7 days", sentiment);

        }
    }
}
