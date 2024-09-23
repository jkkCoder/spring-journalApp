package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class QuestionsResponse{
    private boolean success;
    private ArrayList<Question> questions;
    private int totalQuestions;

    @Getter
    @Setter
    public static class TestCase{
        private String input;
        private String expectedOutput;
        private String explanation;
        private String _id;
    }

    @Getter
    @Setter
    public static class Question{
        private String _id;
        private String title;
        private String description;
        private String difficulty;
        private ArrayList<TestCase> testCases;
        @JsonProperty("Category")
        public String category;
    }
}
