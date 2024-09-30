package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.QuestionsResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
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

    @Autowired
    private AppCache appCache;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;


    public QuestionsResponse getQuestions() {
        QuestionsResponse codingQuestion = redisService.get("coding_question", QuestionsResponse.class);
        if(codingQuestion != null){
            return codingQuestion;
        }else {
            String API = appCache.APP_CACHE.get(AppCache.keys.QUESTION_API.toString()) + "question/filterQuestions?difficulty=&status=&userId=&itemsPerPage=15&page=1";
            ResponseEntity<QuestionsResponse> res= restTemplate.exchange(API, HttpMethod.GET, null, QuestionsResponse.class);
            QuestionsResponse body = res.getBody();
            redisService.set("coding_question", body, 30l);
            return body;
        }
    }

}
