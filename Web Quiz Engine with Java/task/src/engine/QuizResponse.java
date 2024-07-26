package engine;

import java.util.List;

public class QuizResponse {
    private Quiz quiz;
    Long id;
    private String title;
    private String text;
    private List<String> options;

    public QuizResponse(Quiz quiz){
        id = quiz.getId();
        title = quiz.getTitle();
        text = quiz.getText();
        options = quiz.getOptions();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}
