package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.QuestionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Slf4j
@Component
public class CodingQuestionService {
    @Value("${questions.api.endpoint}")
    private String apiEndpoint;
    private String API = apiEndpoint + "question/filterQuestions?difficulty=&status=&userId=&itemsPerPage=15&page=1";

    @Autowired
    private RestTemplate restTemplate;


    public QuestionsResponse getQuestions() {
        log.info(apiEndpoint);
        ResponseEntity<QuestionsResponse> res= restTemplate.exchange(API, HttpMethod.GET, null, QuestionsResponse.class);
        QuestionsResponse body = res.getBody();
        return body;
    }

}
