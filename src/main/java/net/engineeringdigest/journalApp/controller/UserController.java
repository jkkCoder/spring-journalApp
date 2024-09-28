package net.engineeringdigest.journalApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.QuestionsResponse;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import net.engineeringdigest.journalApp.service.CodingQuestionService;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepostiory;

    @Autowired
    private CodingQuestionService codingQuestionService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User oldUser = userService.findByUserName(userName);

        if(oldUser!=null){
            oldUser.setUserName(user.getUserName());
            oldUser.setPassword(user.getPassword());
            userService.saveNewUser(oldUser);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepostiory.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            QuestionsResponse res = codingQuestionService.getQuestions();
            String firstQuestion = res.getQuestions().get(0).getTitle();
            String firstQuestionDesc = res.getQuestions().get(0).getDescription();

            String response = ",\nTitle is : " + firstQuestion + " \nDescription is : " + firstQuestionDesc;
            return new ResponseEntity<>("Hi " +authentication.getName() + response, HttpStatus.OK);
        } catch(Exception err) {
            System.out.println(err);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

}
