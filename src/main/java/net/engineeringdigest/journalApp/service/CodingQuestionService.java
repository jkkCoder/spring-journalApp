package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.QuestionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Component
public class CodingQuestionService {
    private static final String API = "https://letscode.adaptable.app/question/filterQuestions?difficulty=&status=&userId=&itemsPerPage=15&page=1";

    @Autowired
    private RestTemplate restTemplate;

    public QuestionsResponse getQuestions() {
        ResponseEntity<QuestionsResponse> res= restTemplate.exchange(API, HttpMethod.GET, null, QuestionsResponse.class);
        QuestionsResponse body = res.getBody();
        return body;
    }

}
