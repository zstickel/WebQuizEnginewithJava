package engine;

import java.time.LocalDateTime;

public class CompletedQuizDTO {
    private Long id;
    private LocalDateTime completedAt;

    public CompletedQuizDTO(CompletedQuiz completedQuiz) {
        this.id = completedQuiz.getQuiz().getId();
        this.completedAt = completedQuiz.getCompletedAt();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
}
