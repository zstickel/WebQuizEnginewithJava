package engine;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class QuizRequest {

    @NotNull(message = "Title is required.")
    @NotEmpty(message = "Title cannot be empty.")
    private String title;

    @NotNull(message = "Text is required.")
    @NotEmpty(message = "Text cannot be empty.")
    private String text;

    @NotNull(message = "Options are required.")
    @Size(min = 2, message = "Options must contain at least 2 items.")
    private List<String> options;
    private List<Integer> answer;

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public String toString(){
        return "Title: " + title + " Text: " + text;
    }
}

